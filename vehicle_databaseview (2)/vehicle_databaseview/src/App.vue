<script setup>
import MyHeader from '@/components/Header.vue'
import { ref, onMounted, watch, nextTick } from 'vue'
import axios from 'axios'
import { useDataStore } from '@/stores/data'                //存储选择的车辆数据。
const dataStore = useDataStore()
let times = ref([])
let time = ref(null)
const getTimes = async () => {
  try {
    await axios.get('http://127.0.0.1:9000/vehicle/getTimes').then((response) => {
      if (response.data.code == 1) {
        times.value = response.data.data
        time.value = times.value[0]
      } else {
        alert('数据异常')
      }
    })
  } catch (error) {
    console.error('Network Error:', error)
  }
}

const getData = async (selectTime) => {
  //console.log(selectTime)
  try {
    await axios({
      method: 'post',
      url: 'http://127.0.0.1:9000/vehicle/getDataByTime',
      data: selectTime,
      headers: {
        'Content-Type': 'text/plain'
      }
    }).then((response) => {
      //console.log(response.data.data)
      //let data = ref(response.data.data)
      dataStore.changeSelectDataList(response.data.data)
      //console.log(dataStore.selectDataList)
    })
  } catch (error) {
    console.error('Network Error:', error)
  }
}

watch(time, () => {
  nextTick(() => {
    getData(time.value)
    //console.log(time.value)
  })
})

onMounted(() => {
  getTimes()
})
</script>

<template>
  <div class="header">
    <MyHeader></MyHeader>
  </div>
  <div class="navigation-bar">
    <select class="select-time" v-show="dataStore.index == 1" v-model="time">
      <option v-for="(item, index) in times" :value="item" :key="index">{{ item }}</option>
    </select>
    <router-link to="/history">历史数据</router-link>
    <router-link to="/realtime">实时数据</router-link>
  </div>
  <router-view></router-view>
</template>

<style scoped>
@font-face {
  font-family: 'UnidreamLED';
  src: url('@/assets/Digiface-Regular.ttf');
}

.navigation-bar {
  display: flex;
  justify-content: center;
  width: 100%;
  padding-bottom: 0.1rem;
  align-items: center;
}

.select-time {
  position: absolute;
  left: 0.1rem;
  background: transparent;
  color: white;
  border: none;
  font-size: 0.25rem;
  font-family: 'UnidreamLED';
}

.select-time option {
  background: black;
}

a {
  text-decoration: none;
  font-size: 0.2rem;
  font-weight: 700;
  margin-right: 0.2rem;
  color: gray;
  border: 2px solid #217093;
  padding: 0.02rem 0.2rem;
  border-radius: 5%;
}

a.router-link-active {
  color: white;
}
</style>
