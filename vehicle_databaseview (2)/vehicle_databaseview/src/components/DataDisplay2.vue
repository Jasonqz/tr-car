<script setup>
import * as echarts from 'echarts'
import { ref, onMounted, watch } from 'vue'
import { useDataStore } from '@/stores/data'
const dataStore = useDataStore()
let pieChart = ref()
const box = ref(null)
let data = [
  { value: dataStore.selectDataList.length * 5, name: '和平' },
  { value: dataStore.selectDataList.length, name: '走神' },
  { value: dataStore.selectDataList.length * 2, name: '紧张' }
]

watch(
  () => dataStore.selectDataList,
  () => {
    console.log(dataStore.selectDataList.length)
    data = [
      { value: dataStore.selectDataList.length * 5, name: '和平' },
      { value: dataStore.selectDataList.length, name: '走神' },
      { value: dataStore.selectDataList.length * 2, name: '紧张' }
    ]
    init()
  },
  {
    deep: true
  }
)

let myChart

const init = () => {
  myChart = echarts.init(pieChart.value)
  let containerWidth = box.value.offsetWidth
  let fontSize1 = Math.min(containerWidth / 25, 40)
  let fontSize2 = Math.min(containerWidth / 10, 40)
  let option = {
    tooltip: {
      trigger: 'item'
    },
    color:['#BED0A6', '#FF9999', '#FAC858'],
    legend: {
      type: 'scroll',
      orient: 'vertical',
      right: 15,
      top: 10,
      bottom: 20,
      //right: 0, // 图例距离容器右侧 5%
      //left: 'center', // 确保图例不会被水平居中
      //top: 'center', // 图例垂直居中
      textStyle: {
        color: 'white',
        fontSize: fontSize1
      },
      itemWidth: 14, // 图例项的宽度
      itemHeight: 10, // 图例项的高度
      itemGap: 15 // 图例项之间的间隔
    },
    series: [
      {
        //name: 'Access From',
        type: 'pie',
        radius: ['40%', '80%'],
        center: ['40%', '50%'],
        avoidLabelOverlap: false,
        itemStyle: {
          borderRadius: 10,
          borderColor: '#fff',
          borderWidth: 2
        },
        label: {
          show: false,
          position: 'center'
        },
        emphasis: {
          label: {
            show: true,
            fontSize: fontSize2,
            fontWeight: 'bold'
          }
        },
        labelLine: {
          show: false
        },
        data: data
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
})
</script>

<template>
  <div class="box" ref="box">
    <div class="title">驾驶员状态信息</div>
    <div class="pie" ref="pieChart"></div>
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

.pie {
  width: 100%;
  height: 85%;
  overflow: hidden;
}
</style>
