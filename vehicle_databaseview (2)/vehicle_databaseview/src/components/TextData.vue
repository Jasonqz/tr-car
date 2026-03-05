<script setup>
import DataBox from '@/components/DataBox.vue'
//import axios from 'axios'
import { ref, onMounted } from 'vue'
import { useDataStore } from '@/stores/data'
const dataStore = useDataStore()
const collection = new Map([
  ['speed', '速度'],
  ['acceleration', '加速度'],
  ['temperature', '温度'],
  ['pressure', '气压'],
  ['height', '高度']
])
const keysToKeep = ['speed', 'acceleration', 'temperature', 'pressure', 'height'] // 需要保留的属性名
const units = ['m/s', 'm/s2', '℃', 'Pa', 'm']
let oldObject = null
const getValue = (key) => {
  return collection.get(key)
}
let dataList = ref(null)
let count = 0
let defaultList = ref({
  speed: -1,
  acceleration: -1,
  temperature: -1,
  pressure: -1,
  height: -1
})
const showData = () => {
  //console.log(dataStore.selectDataList)
  if (dataStore.selectDataList != null) {
    //console.log(dataStore.selectDataList)
    if (count < dataStore.selectDataList.length) {
      oldObject = dataStore.selectDataList[count]
      dataList.value = Object.keys(oldObject).reduce((acc, key) => {
        if (keysToKeep.includes(key)) {
          acc[key] = oldObject[key] // 仅保留指定属性
        }
        return acc
      }, {})
      //delete dataList.value['time']
      //console.log(dataList.value)
      count++
    } else {
      count = 1
      oldObject = dataStore.selectDataList[0]
      dataList.value = Object.keys(oldObject).reduce((acc, key) => {
        if (keysToKeep.includes(key)) {
          acc[key] = oldObject[key] // 仅保留指定属性
        }
        return acc
      }, {})
      //delete dataList.value['time']
    }
  } else {
    dataList.value = defaultList.value
  }
}
onMounted(() => {
  setInterval(showData, 3000)
})
</script>

<template>
  <div class="box">
    <div class="title">数据可视化测试</div>
    <div class="frame">
      <div class="column">
        <ul class="container">
          <li v-for="(value, key, index) in dataList" :key="key">
            <DataBox>
              <template v-slot:title>{{ getValue(key) }}</template>
              <template v-slot:data>{{ value }}</template>
              <template v-slot:unit v-if="index != 1">{{ units[index] }}</template>
              <template v-slot:unit v-else>m/s<sup>2</sup></template>
            </DataBox>
          </li>
        </ul>
        <ul class="container">
          <li v-for="(value, key, index) in dataList" :key="key">
            <DataBox>
              <template v-slot:title>{{ getValue(key) }}</template>
              <template v-slot:data>{{ value }}</template>
              <template v-slot:unit v-if="index != 1">{{ units[index] }}</template>
              <template v-slot:unit v-else>m/s<sup>2</sup></template>
            </DataBox>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<style scoped>
.box {
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

.frame {
  overflow: hidden;
}

.column {
  animation: move 10s infinite linear;
}

.container {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
  overflow: hidden;
}

.container::after {
  content: '';
  width: 3.5rem;
}

@keyframes move {
  0% {
    transform: translateY(0);
  }

  100% {
    transform: translateY(-50%);
  }
}
</style>
