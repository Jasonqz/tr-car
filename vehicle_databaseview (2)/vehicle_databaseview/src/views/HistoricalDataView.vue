<script setup>
import BaseInfo from '@/components/BaseInformation.vue'
import PageInfo from '@/components/PageInformation.vue'
import DataDisplay1 from '@/components/DataDisplay1.vue'
//import FontScroll from '@/components/FontScroll.vue'
//import MyEarth3D from '@/components/Earth3D.vue'
import DataDisplay2 from '@/components/DataDisplay2.vue'
//import DynamicData from '@/components/DynamicData.vue'
//import TemperatureDisplay from '@/components/TemperatureDisplay.vue'
//import DataBox from '@/components/DataBox.vue'
import TextData from '@/components/TextData.vue'
import AirQuality from '@/components/AirQuality.vue'
import FrontImage from '@/components/FrontImage.vue'
import InteriorImage from '@/components/InteriorImage.vue'
</script>

<template>
  <div class="content">
    <div class="left">
      <div class="left-top style1"><BaseInfo></BaseInfo></div>
      <div class="left-middle style1"><PageInfo></PageInfo></div>
      <div class="left-bottom style1"><FrontImage></FrontImage></div>
    </div>
    <div class="center">
      <div class="center-top style1"><DataDisplay1></DataDisplay1></div>
      <div class="center-bottom style1"><TextData></TextData></div>
    </div>
    <div class="right">
      <div class="right-top style1"><AirQuality></AirQuality></div>
      <div class="right-middle style1"><DataDisplay2></DataDisplay2></div>
      <div class="right-bottom style1"><InteriorImage></InteriorImage></div>
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
