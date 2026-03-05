<script setup>
import axios from 'axios'
import { ref, onMounted } from 'vue'
import { useDataStore } from '@/stores/data'
const dataStore = useDataStore()
let voiceSum = ref(-1)
let imgSum = ref(-1)
let textSum = ref(-1)
let sum = ref(-1)
const getCount = async () => {
  await axios
    .get('http://127.0.0.1:9000/vehicle/getCounts')
    .then((response) => {
      //console.log(response.data)
      voiceSum.value = response.data.data
      dataStore.changeBaseCount(response.data.data)
      imgSum.value = voiceSum.value * 2
      textSum.value = voiceSum.value * 5
      sum.value = voiceSum.value + textSum.value + imgSum.value
    })
    .catch((error) => {
      console.error('Network Error:', error)
    })
}
onMounted(() => {
  getCount()
})
defineOptions({
  name: 'BaseInfo'
})
</script>

<template>
  <div class="box">
    <div class="title">基本信息</div>
    <div class="introduce">
      <p>
        大数据可视化是指利用计算机图形学、‌图像处理等技术，‌将大规模、‌复杂的数据转换成易于理解的视觉元素，‌通过直观的方式呈现出来，‌帮助人们更好地理解和分析数据。‌
        ‌
      </p>
    </div>
    <div class="data">
      <ul>
        <li>数据总量：{{ sum }}</li>
        <li>图片数量：{{ imgSum }}</li>
        <li>文本数量：25</li>
        <li>音频数量：{{ voiceSum }}</li>
        <li>待定数据量：xxx</li>
        <li>待定数据量：xxx</li>
      </ul>
    </div>
  </div>
</template>

<style scoped>
.box {
  padding: 0 0.2rem;
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
  font-size: 0.25rem;
}

.introduce {
  padding-left: 0.12rem;
  margin-bottom: 0.2rem;
  margin-top: 0.3rem;
  font-size: 0.25rem;
}

p {
  color: #029698;
}

.data {
  color: white;
  padding-left: 0.12rem;
  font-size: 0.25rem;
}

ul {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

li {
  height: 0.5rem;
  width: 2.5rem;
  padding: 0 0.2rem 0.2rem 0;
}

/* ul::after {
  content: '';
  height: 0.5rem;
  width: 2.5rem;
} */
</style>
