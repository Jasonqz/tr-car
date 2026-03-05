<script setup>
import { watch } from 'vue'
import { useRouter } from 'vue-router'
import { useDataStore } from '@/stores/data'
const router = useRouter()
const dataStore = useDataStore()
watch(
  dataStore.index,
  () => {
    console.log(dataStore.index)
    if (dataStore.index != 1) {
      //router.push('/realtime')
      alert('当前不在行使状态')
      //setTimeout(window.history.back(), 5000)
      router.push('/history')
    }
  },
  {
    immediate: true
  }
)
</script>

<template>
  <div class="content">
    <div class="left">
      <div class="left-top style1"></div>
      <div class="left-middle style1"></div>
      <div class="left-bottom style1"></div>
    </div>
    <div class="center">
      <div class="center-top style1"></div>
      <div class="center-bottom style1"></div>
    </div>
    <div class="right">
      <div class="right-top style1"></div>
      <div class="right-middle style1"></div>
      <div class="right-bottom style1"></div>
    </div>
  </div>
</template>

<style scoped>
.style1 {
  margin: 0 0.1rem 0.1rem 0.1rem;
  border: 2px solid #217093;
}

.content {
  display: flex;
  height: 11.5rem;
}

.content .left {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
}

.content .left .left-top {
  flex: 1;
}

.content .left .left-middle {
  flex: 1;
}

.content .left .left-bottom {
  flex: 1;
}

.content .center {
  flex: 2;
  display: flex;
  flex-direction: column;
}

.content .center .center-top {
  flex: 2;
}

.content .center .center-bottom {
  flex: 1;
  overflow: hidden;
  padding: 0.1rem;
}

.content .right {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
}

.content .right .right-top {
  flex: 1;
  overflow: hidden;
}

.content .right .right-middle {
  flex: 1;
}

.content .right .right-bottom {
  flex: 1;
}
</style>
