import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useDataStore = defineStore('data', () => {
  const index = ref(1)

  let selectDataList = ref([])
  const changeSelectDataList = (list) => {
    selectDataList.value = list
    //console.log(selectDataList.value)
  }

  let baseCount = ref(-1)
  const changeBaseCount = (count) => {
    baseCount.value = count
  }
  //let dottedBase = +new Date()
  //let aimDate = new Date((dottedBase -= 3600 * 24 * 1000))
  return {
    index,
    selectDataList,
    changeSelectDataList,
    baseCount,
    changeBaseCount
  }
})
