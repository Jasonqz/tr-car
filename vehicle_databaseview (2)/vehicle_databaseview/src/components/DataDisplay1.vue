<script setup>
import * as echarts from 'echarts'
import { ref, onMounted } from 'vue'
import axios from 'axios'
let voiceSum = ref(-1)
let number = ref([])
let flag = true
let sum = ref(-1)
const getCount = async () => {
  await axios
    .get('http://127.0.0.1:9000/vehicle/getCounts')
    .then((response) => {
      //console.log(response.data.data)
      voiceSum.value = response.data.data
      sum.value = voiceSum.value * 8
      let i = 10
      while (flag) {
        if (sum.value >= i) {
          number.value.push(sum.value % i)
          sum.value = Math.trunc(sum.value / i)
        } else {
          number.value.push(sum.value)
          number.value = number.value.reverse()
          //console.log(number)
          break
        }
      }
    })
    .catch((error) => {
      console.error('Network Error:', error)
    })
}
const chart = ref()
let myChart

const init = async () => {
  myChart = echarts.init(chart.value)
  var option

  // Generate data
  let category = []
  let dottedBase = +new Date()
  let lineData = []
  let barData = []
  let textData = []
  for (let i = 0; i < 20; i++) {
    let date = new Date((dottedBase -= 3600 * 24 * 1000))
    let year = date.getFullYear()
    let month = date.getMonth() + 1 >= 10 ? date.getMonth() + 1 : '0' + (date.getMonth() + 1)
    let day = date.getDate() + 1 >= 10 ? date.getDate() + 1 : '0' + (date.getDate() + 1)
    let time = [year, month, day].join('-')
    category.push(time)
    await axios({
      method: 'post',
      url: 'http://127.0.0.1:9000/vehicle/getDataByTime',
      data: time,
      headers: {
        'Content-Type': 'text/plain'
      }
    }).then((response) => {
      let b = response.data.data.length
      let d = response.data.data.length * 2
      let c = response.data.data.length * 5
      barData.push(b)
      lineData.push(d)
      textData.push(c)
    })
  }
  category = category.reverse()
  barData = barData.reverse()
  lineData = lineData.reverse()
  textData = textData.reverse()
  // option
  option = {
    tooltip: {
      trigger: 'axis',
      // formatter: function (params) {
      //   // 只显示前两个数据系列
      //   let seriesData = params.filter((item) => item.seriesIndex < 2)
      //   let tooltipText = seriesData
      //     .map((item) => {
      //       return `${item.seriesName}: ${item.data}`
      //     })
      //     .join('<br>')
      //   return tooltipText
      // },
      axisPointer: {
        type: 'shadow'
      }
    },
    // 图例样式
    legend: {
      data: ['图片', '音频', '文本'], //图例包含折线和柱状
      top: '5%',
      textStyle: {
        color: '#ccc', //字体颜色
        fontSize: 12, // 图例文本大小
        fontWeight: 'normal' // 图例文本粗细
      }
      // bottom: '5%'
    },
    grid: {
      top: '20%', // Increase this value to push the chart down
      bottom: '15%' // Decrease this value to push the chart up
    },
    // y轴的相关设置
    yAxis: {
      name: '单位: 个', // y轴顶部显示单位
      nameLocation: 'end', // 显示在y轴的末端
      nameTextStyle: {
        color: '#ccc', // 单位的文字颜色
        fontSize: 12, // 单位的文字大小
        padding: [0, 0, 10, 0] // 调整位置，使其靠近轴线
      },
      splitLine: {
        show: true,
        lineStyle: {
          type: 'dashed'
        }
      }, // 隐藏y轴分割线
      axisLine: {
        lineStyle: {
          color: '#ccc' // y轴颜色
        },
        axisLabel: {
          interval: 'auto', // 隔多少在x轴上显示一个
          showMaxLabel: true // 显示最后一个标签
        }
      }
    },
    // x轴的相关设置
    xAxis: {
      data: category, // x轴的数据
      axisLine: {
        lineStyle: {
          color: '#ccc' // x轴的颜色
        }
      },
      axisTick: {
        alignWithLabel: true // 使刻度线与标签对齐
      },
      splitLine: {
        // 自定义分割线
        show: false,
        lineStyle: {
          color: '#ccc', // 分割线颜色
          type: 'solid' // 分割线类型
        }
      },
      axisLabel: {
        interval: 3, // 隔多少在x轴上显示一个
        showMaxLabel: true // 显示最后一个标签
      }
    },
    // 图表相关设置
    series: [
      {
        name: '图片',
        type: 'line',
        smooth: true,
        showAllSymbol: true,
        symbol: 'emptyCircle',
        symbolSize: 15,
        data: lineData
      },
      {
        name: '音频',
        type: 'bar',
        barWidth: 10,
        itemStyle: {
          borderRadius: 5,
          color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
            { offset: 0, color: '#14c8d4' }, // 渐变色的起始色
            { offset: 1, color: '#43eec6' } //渐变色的结束色
          ])
        },
        data: barData
      },
      {
        name: '文本',
        type: 'line',
        smooth: true,
        showAllSymbol: true,
        symbol: 'emptyCircle',
        symbolSize: 15,
        data: textData
      }
      // {
      //   name: 'line',
      //   type: 'bar',
      //   barGap: '-100%',
      //   barWidth: 10,
      //   itemStyle: {
      //     color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
      //       { offset: 0, color: 'rgba(20,200,212,0.5)' },
      //       { offset: 0.2, color: 'rgba(20,200,212,0.2)' },
      //       { offset: 1, color: 'rgba(20,200,212,0)' }
      //     ])
      //   },
      //   z: -12,
      //   data: lineData
      // },
      // {
      //   name: 'dotted',
      //   type: 'pictorialBar',
      //   symbol: 'rect',
      //   itemStyle: {
      //     color: '#0f375f'
      //   },
      //   symbolRepeat: true,
      //   symbolSize: [12, 4],
      //   symbolMargin: 1,
      //   z: -10,
      //   data: lineData
      // }
    ]
  }

  myChart.setOption(option)
}
const resizeChart = () => {
  if (myChart) {
    myChart.resize()
  }
}
// onBeforeUnmount(() => {
//   window.removeEventListener('resize', resizeChart)
// })
onMounted(() => {
  getCount()
  init()
  window.addEventListener('resize', resizeChart)
})
</script>

<template>
  <div class="counter">
    <ul>
      <li>
        <div>数量统计</div>
        <div style="display: flex; align-items: center">
          <span v-for="(item, index) in number" :key="index">{{ item }}</span
          ><b>个</b>
        </div>
      </li>
      <li>
        <div>同比上升</div>
        <div style="display: flex; align-items: center"><span>1</span><span>3</span><b>%</b></div>
      </li>
    </ul>
  </div>
  <div class="chart1" ref="chart"></div>
</template>

<style scoped>
.counter {
  width: 100%;
  height: 20%;
  font-size: 0.25rem;
}

ul {
  display: flex;
  height: 100%;
  padding-left: 0.5rem;
}

li {
  height: 100%;
  width: 2.75rem;
  color: white;
  padding: 0.2rem;
}

@font-face {
  font-family: 'UnidreamLED';
  src: url(../assets/Digiface-Regular.ttf);
}

span {
  display: block;
  height: 0.7rem;
  width: 0.5rem;
  background-color: #37a9ea;
  margin-top: 0.1rem;
  margin-right: 0.1rem;
  text-align: center;
  line-height: 0.7rem;
  font-size: 0.6rem;
  font-weight: 400;
  font-family: 'UnidreamLED';
}

.chart1 {
  width: 100%;
  height: 80%;
}
</style>
