<script setup>
import * as echarts from 'echarts'
import { ref, onMounted } from 'vue'
let app = {}

let barChart = ref()
let myChart

const categories = (function () {
  let now = new Date()
  let res = []
  let len = 10
  while (len--) {
    res.unshift(now.toLocaleTimeString().replace(/^\D*/, ''))
    now = new Date(+now - 2000)
  }
  return res
})()
const categories2 = (function () {
  let res = []
  let len = 10
  while (len--) {
    res.push(10 - len - 1)
  }
  return res
})()
const data = (function () {
  let res = []
  let len = 10
  while (len--) {
    res.push(Math.round(Math.random() * 1000))
  }
  return res
})()
const data2 = (function () {
  let res = []
  let len = 0
  while (len < 10) {
    res.push(+(Math.random() * 10 + 5).toFixed(1))
    len++
  }
  return res
})()

const init = () => {
  myChart = echarts.init(barChart.value)
  let option = {
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'cross',
        label: {
          backgroundColor: '#283b56'
        }
      }
    },
    legend: {
      textStyle: {
        color: 'white'
      }
    },
    toolbox: {
      show: true,
      feature: {
        dataView: { readOnly: false },
        restore: {},
        saveAsImage: {}
      }
    },
    dataZoom: {
      show: false,
      start: 0,
      end: 100
    },
    grid: {
      top: '30%', // Increase this value to push the chart down
      bottom: '20%' // Decrease this value to push the chart up
    },
    xAxis: [
      {
        type: 'category',
        boundaryGap: true,
        data: categories,
        axisLine: {
          lineStyle: {
            color: 'white'
          }
        }
      },
      {
        type: 'category',
        boundaryGap: true,
        data: categories2,
        axisLine: {
          lineStyle: {
            color: 'orange'
          }
        }
      }
    ],
    yAxis: [
      {
        type: 'value',
        scale: true,
        name: 'Price',
        max: 30,
        min: 0,
        boundaryGap: [0.2, 0.2],
        axisLine: {
          lineStyle: {
            color: 'white'
          }
        }
      },
      {
        type: 'value',
        scale: true,
        name: 'Order',
        max: 1200,
        min: 0,
        boundaryGap: [0.2, 0.2],
        axisLine: {
          lineStyle: {
            color: '#029698'
          }
        }
      }
    ],
    series: [
      {
        name: '价钱',
        type: 'bar',
        xAxisIndex: 1,
        yAxisIndex: 1,
        data: data
      },
      {
        name: '销量',
        type: 'line',
        data: data2
      }
    ]
  }
  app.count = 11
  setInterval(function () {
    let axisData = new Date().toLocaleTimeString().replace(/^\D*/, '')
    data.shift()
    data.push(Math.round(Math.random() * 1000))
    data2.shift()
    data2.push(+(Math.random() * 10 + 5).toFixed(1))
    categories.shift()
    categories.push(axisData)
    categories2.shift()
    categories2.push(app.count++)
    myChart.setOption({
      xAxis: [
        {
          data: categories
        },
        {
          data: categories2
        }
      ],
      series: [
        {
          data: data
        },
        {
          data: data2
        }
      ]
    })
  }, 2100)

  myChart.setOption(option)
}
const resizeChart = () => {
  if (myChart) {
    myChart.resize()
  }
}
onMounted(() => {
  init()
  window.addEventListener('resize', resizeChart)
})
</script>

<template>
  <div class="box">
    <div class="title">数据可视化测试</div>
    <div class="bar" ref="barChart"></div>
  </div>
</template>

<style scoped>
.box {
  padding: 0 0.2rem;
  font-size: 0.25rem;
  height: 100%;
  width: 100%;
}

.title:before {
  width: 0.05rem;
  height: 0.25rem;
  /* top: 0.02rem; */
  position: absolute;
  content: '';
  background: #59ebe8;
  border-radius: 0.02rem;
  left: 0;
}

.title {
  width: 90%;
  height: 0.3rem;
  /* border: 1px solid gold; */
  position: relative;
  top: 0.1rem;
  /* left: 0.25rem; */
  color: white;
  padding-left: 0.12rem;
  line-height: 0.3rem;
  margin-bottom: 0.2rem;
}

.bar {
  height: 90%;
  width: 100%;
}
</style>
