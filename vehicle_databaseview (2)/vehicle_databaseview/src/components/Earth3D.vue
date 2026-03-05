<script setup>
import * as echarts from 'echarts'
import { ref, onMounted } from 'vue'

import baseTexture from '@/assets/world.topo.bathy.200401.jpg'
import heightTexture from '@/assets/world.topo.bathy.200401.jpg'
import environment from '@/assets/bg01.png'
import ambientCubemapTexture from '@/assets/pisa.hdr'
let myChart
let earthChart = ref()
const init = () => {
  myChart = echarts.init(earthChart.value)
  let option = {
    backgroundColor: 'transparent',
    globe: {
      baseTexture: baseTexture,
      heightTexture: heightTexture,
      displacementScale: 0.04,
      shading: 'realistic',
      environment: environment,
      realisticMaterial: {
        roughness: 0.9
      },
      postEffect: {
        enable: true
      },
      light: {
        main: {
          intensity: 5,
          shadow: true
        },
        ambientCubemap: {
          texture: ambientCubemapTexture,
          diffuseIntensity: 0.2
        }
      },
      viewControl: {
        autoRotate: true, // 启用地球自动旋转
        autoRotateSpeed: 5, // 设置地球自动旋转速度
        targetCoord: [116.46, 39.92], // 设置地球旋转的中心坐标（例如北京）
        damping: 0.9 // 设置阻尼效果，使旋转更平滑
      }
    }
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
  <div id="earth" ref="earthChart"></div>
</template>

<style scoped>
#earth {
  height: 100%;
  width: 100%;
}
</style>
