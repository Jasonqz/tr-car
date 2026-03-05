<script setup>
import * as echarts from 'echarts'
import { ref, onMounted } from 'vue'
let gaugeChart = ref()
let myChart
const init = () => {
  myChart = echarts.init(gaugeChart.value)
  let option = {
    series: [
      {
        type: 'gauge',
        center: ['50%', '70%'],
        startAngle: 200,
        endAngle: -20,
        min: 0,
        max: 60,
        splitNumber: 12,
        progress: {
          show: false,
          width: 30
        },
        pointer: {
          itemStyle: {
            color: 'auto'
          },
          width: 3
        },
        axisLine: {
          lineStyle: {
            width: 20,
            color: [
              [0.3, '#67e0e3'],
              [0.7, '#37a2da'],
              [1, '#fd666d']
            ]
          }
        },
        axisTick: {
          distance: -40,
          splitNumber: 5,
          lineStyle: {
            width: 2,
            color: '#999'
          }
        },
        splitLine: {
          distance: -44,
          length: 7,
          lineStyle: {
            width: 2,
            color: '#999'
          }
        },
        axisLabel: {
          distance: -10,
          color: '#999',
          fontSize: 15
        },
        anchor: {
          show: false
        },
        title: {
          show: false
        },
        detail: {
          valueAnimation: true,
          width: '60%',
          lineHeight: 0,
          borderRadius: 8,
          offsetCenter: [0, '45%'],
          fontSize: 20,
          fontWeight: 'bolder',
          formatter: '{value} °C',
          color: 'auto'
        },
        data: [
          {
            value: 20
          }
        ]
      }
    ]
  }
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
  setInterval(function () {
    const random = +(Math.random() * 60).toFixed(2)
    myChart.setOption({
      series: [
        {
          data: [
            {
              value: random
            }
          ]
        },
        {
          data: [
            {
              value: random
            }
          ]
        }
      ]
    })
  }, 2000)
})
</script>

<template>
  <div class="box">
    <div class="title">数据可视化测试</div>
    <div class="chart" ref="gaugeChart"></div>
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

.chart {
  height: 90%;
  width: 100%;
}
</style>
