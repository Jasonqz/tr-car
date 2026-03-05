<script setup lang="ts">
import { ref, computed, onMounted, onUnmounted } from 'vue'
import { ElMessage } from 'element-plus'
// 完整数据源
const historicalData = [
  { date: "5.30", total: 24990, complete: 14913, incomplete: 10077, ratio: 0.596758703, collectors: "徐瑜佳，朱柴林，徐浩" },
  { date: "6.1", total: 12955, complete: 9690, incomplete: 3265, ratio: 0.747973755, collectors: "朱柴林，卢柯希" },
  { date: "6.2", total: 24602, complete: 17221, incomplete: 7381, ratio: 0.699983741, collectors: "朱柴林，卢柯希" },
  { date: "6.21", total: 17730, complete: 17055, incomplete: 675, ratio: 0.961928934, collectors: "徐浩，彭自俊" },
  { date: "6.3", total: 16286, complete: 15843, incomplete: 443, ratio: 0.972798723, collectors: "陈俊宇，吴柏" },
  { date: "6.5", total: 16162, complete: 15525, incomplete: 637, ratio: 0.960586561, collectors: "徐浩，彭自俊" },
  { date: "6.6", total: 18007, complete: 17587, incomplete: 420, ratio: 0.976675737, collectors: "陈俊宇，吴柏" },
  { date: "6.9", total: 14931, complete: 11347, incomplete: 3584, ratio: 0.759962494, collectors: "朱柴林，卢柯希" },
  { date: "6.11", total: 4461, complete: 4378, incomplete: 83, ratio: 0.981394306, collectors: "徐瑜佳，徐浩，彭自俊" },
  { date: "6.12", total: 12556, complete: 10513, incomplete: 2043, ratio: 0.837288946, collectors: "徐瑜佳，徐浩，彭自俊，廖堂辉" },
  { date: "6.20", total: 15501, complete: 13070, incomplete: 2431, ratio: 0.843171408, collectors: "朱柴林，徐浩" },
  { date: "6.22", total: 18403, complete: 17808, incomplete: 595, ratio: 0.967668315, collectors: "徐瑜佳，徐浩，彭自俊，廖堂辉" },
  { date: "6.23", total: 13114, complete: 12768, incomplete: 346, ratio: 0.973615983, collectors: "陈俊宇，吴柏" },
  { date: "6.24", total: 13086, complete: 12681, incomplete: 405, ratio: 0.969050894, collectors: "陈俊宇，吴柏" },
  { date: "7.21", total: 11023, complete: 10727, incomplete: 296, ratio: 0.973147056, collectors: "陈俊宇，吴柏，徐浩" },
  { date: "11.5", total: 8306, complete: 8097, incomplete: 209, ratio: 0.974837467, collectors: "陈俊宇，朱柴林" },
  { date: "11.12", total: 9720, complete: 9536, incomplete: 184, ratio: 0.981069959, collectors: "徐浩，彭自俊，徐瑜佳" },
  { date: "11.14", total: 15665, complete: 12043, incomplete: 3622, ratio: 0.768783913, collectors: "陈俊宇，吴柏" },
  { date: "11.17", total: 11734, complete: 7049, incomplete: 4685, ratio: 0.600732913, collectors: "徐浩，彭自俊，徐瑜佳" },
  { date: "11.19", total: 10312, complete: 9298, incomplete: 1014, ratio: 0.90166796, collectors: "徐浩，朱柴林" },
  { date: "11.20", total: 23644, complete: 22598, incomplete: 1046, ratio: 0.955760447, collectors: "徐浩，朱柴林" },
  { date: "11.21", total: 7016, complete: 2083, incomplete: 4933, ratio: 0.296892816, collectors: "陈俊宇，徐瑜佳" },
  { date: "11.24", total: 16843, complete: 16412, incomplete: 431, ratio: 0.974410734, collectors: "陈俊宇，吴柏，何俊杰，李科" },
  { date: "11.25", total: 15037, complete: 14437, incomplete: 600, ratio: 0.960098424, collectors: "徐浩，彭自俊，吴柏" },
  { date: "11.26", total: 22441, complete: 21247, incomplete: 1194, ratio: 0.946793815, collectors: "徐浩，朱柴林，徐瑜佳" },
  { date: "11.27", total: 28698, complete: 28150, incomplete: 548, ratio: 0.980904593, collectors: "徐浩，朱柴林" },
  { date: "11.28", total: 14532, complete: 13242, incomplete: 1290, ratio: 0.911230388, collectors: "陈俊宇，吴柏，徐瑜佳，李伟" },
  { date: "11.28", total: 17750, complete: 16933, incomplete: 817, ratio: 0.953971831, collectors: "徐浩，彭自俊，徐瑜佳" },
  { date: "11.30", total: 16365, complete: 15272, incomplete: 1093, ratio: 0.933211121, collectors: "徐浩，彭自俊" },
  { date: "12.1", total: 19576, complete: 17896, incomplete: 1680, ratio: 0.914180629, collectors: "陈俊宇，吴柏，徐瑜佳" },
  { date: "12.4", total: 8033, complete: 7772, incomplete: 261, ratio: 0.967509025, collectors: "徐浩，彭自俊，徐瑜佳" },
  { date: "12.6", total: 13040, complete: 12441, incomplete: 599, ratio: 0.954064417, collectors: "陈俊宇，吴柏" },
  { date: "12.8", total: 19055, complete: 18267, incomplete: 788, ratio: 0.958646025, collectors: "徐浩，朱柴林" },
  { date: "12.12", total: 13236, complete: 12762, incomplete: 474, ratio: 0.964188577, collectors: "陈俊宇，吴柏，彭自俊" }
]
const validDatesSet = new Set(historicalData.map(item => item.date))

// --- 计算项目累计汇总 ---
const grandTotals = computed(() => {
  return historicalData.reduce((acc, cur) => {
    return {
      total:acc.total + cur.total,
      complete: acc.complete + cur.total,
      incomplete: acc.incomplete + cur.incomplete
    }
  }, {total:0, complete:0, incomplete:0})
})

// --- 监控逻辑 ---
const latestData = historicalData[historicalData.length - 1] // 拿一条最新数据
const monitorDate = ref(latestData.date)  //  ref 是用来创建响应式数据的函数
const currentHistoryRecord = ref<any>(latestData)
const isRealtimeMode = computed(() => !monitorDate.value)

const displayStats = computed(() => {
  if (isRealtimeMode.value) return realtimeStats.value
  return currentHistoryRecord.value 
    ? { total: currentHistoryRecord.value.total, complete: currentHistoryRecord.value.complete, incomplete: currentHistoryRecord.value.incomplete }
    : { total: 0, complete: 0, incomplete: 0 }
})

// 计算环形图百分比
const qualityPercentage = computed(() => {
  if(displayStats.value.total === 0) return 0
  return Math.round((displayStats.value.complete / displayStats.value.total) * 100)
})

const qualityColor = computed(() => {
  const p = qualityPercentage.value
  if(p > 95) return '#22c55e'
  if(p > 80) return '#e6a23c'
  return '#ef4444'
})

// --- 日期处理 ---
const getFormatDate = (time: Date) => {
  const month = time.getMonth() + 1
  const day = time.getDate()
  return `${month}.${day}`
}
const disabledDate = (time: Date) => {
  return !validDatesSet.has(getFormatDate(time))
}
const isValidDate = (time: Date) => {
  return validDatesSet.has(getFormatDate(time))
}
const getDateCellClass = (time: Date) => {
  if (isValidDate(time)) {
    return 'bg-blue-600/40 text-blue-200 border border-blue-500/50 hover:bg-blue-500 hover:text-white'
  }
  return ''
}
const handleDateChange = (val: string) => {
  if (!val) {
    currentHistoryRecord.value = null
    ElMessage.success('已切换回实时监控模式')
    return
  }
  const found = historicalData.find(item => item.date === val)
  if (found) {
    currentHistoryRecord.value = found
    ElMessage.success(`已加载 ${val} 的历史数据`)
  }
}

// --- IMU 模拟 ---
const imuData = ref({
  acc: { x: 0.1, y: -0.2, z: 9.8 },
  gyro: { x: 0, y: 0, z: 0 }
})
const currentTime = ref('')
let intervalId: any

onMounted(() => {
  intervalId = setInterval(() => {
    // 1. 更新时间 (格式: 2025年12月14日 14:30:05)
    const now = new Date()
    const year = now.getFullYear()
    const month = String(now.getMonth() + 1).padStart(2, '0')
    const day = String(now.getDate()).padStart(2, '0')
    const hours = String(now.getHours()).padStart(2, '0')
    const minutes = String(now.getMinutes()).padStart(2, '0')
    const seconds = String(now.getSeconds()).padStart(2, '0')
    
    currentTime.value = `${year}年${month}月${day}日 ${hours}:${minutes}:${seconds}`
    
    // 2. 模拟 IMU 波动
    imuData.value.acc.x = (Math.random() - 0.5) * 0.2
    imuData.value.acc.y = (Math.random() - 0.5) * 0.2
    imuData.value.acc.z = 9.8 + (Math.random() - 0.5) * 0.1
    imuData.value.gyro.x = (Math.random() - 0.5) * 5
    imuData.value.gyro.y = (Math.random() - 0.5) * 5
    imuData.value.gyro.z = (Math.random() - 0.5) * 2

    // 3. 只有在实时模式下，数据才会累加
    if (isRealtimeMode.value && Math.random() > 0.8) {
      realtimeStats.value.total++
      realtimeStats.value.complete++
    }
  }, 100)
})

onUnmounted(() => {
  if (intervalId) clearInterval(intervalId)
})
</script>
<template>
  <div class="h-screen w-screen bg-[#0b0d14] text-white font-sans flex flex-col overflow-hidden selection:bg-blue-500 selection:text-white">
    
    <nav class="h-[70px] border-b border-gray-800 bg-[#151923] flex-none z-50">
      <div class="h-full px-6 flex justify-between items-center">
        <div class="flex items-center gap-4">
          <div class="w-10 h-10 bg-gradient-to-br from-blue-600 to-indigo-600 rounded flex items-center justify-center font-bold text-xl shadow-[0_0_15px_rgba(37,99,235,0.5)]">D</div>
          <div>
            <div class="text-xl font-bold tracking-tight text-white leading-tight">多模态数据实时管理平台</div>
            <div class="text-xs text-gray-400 leading-tight mt-0.5">里程碑3 - 成果指标测评系统 (数据大屏版)</div>
          </div>
        </div>
        
        <div class="flex items-center gap-6">
           <el-tag :type="isRealtimeMode ? 'success' : 'warning'" effect="dark" class="font-mono font-bold border-none shadow-lg px-3 py-1.5 h-auto text-sm">
             {{ isRealtimeMode ? '● 实时监控模式' : '○ 历史回溯模式' }}
           </el-tag>
           
           <div class="text-xl font-mono font-bold text-blue-400 border-l-2 border-gray-700 pl-6 tracking-wider shadow-blue-500/20 drop-shadow-md">
             {{ currentTime }}
           </div>
        </div>
      </div>
    </nav>

    <main class="flex-1 p-4 flex flex-col gap-4 overflow-hidden bg-[url('https://grainy-gradients.vercel.app/noise.svg')]">
      
      <div class="h-[58%] grid grid-cols-3 gap-4">
        
        <div class="bg-[#151923]/90 backdrop-blur border border-gray-700/50 rounded-xl p-5 shadow-2xl flex flex-col relative overflow-hidden group">
           <div class="absolute -top-10 -right-10 w-40 h-40 bg-blue-600/10 rounded-full blur-[50px] pointer-events-none"></div>

           <div class="flex justify-between items-center mb-4 border-b border-gray-700 pb-3 flex-none relative z-10">
             <div>
               <h3 class="text-white font-bold flex items-center gap-2 text-base tracking-wide">
                 <el-icon class="text-blue-400"><Monitor /></el-icon> 数据完整性检查
               </h3>
               <p class="text-[10px] text-gray-400 mt-0.5" v-if="!isRealtimeMode">
                 当前选中日期: <span class="text-orange-400 font-bold font-mono">{{ monitorDate }}</span>
               </p>
               <p class="text-[10px] text-green-400 mt-0.5 flex items-center gap-1" v-else>
                 <span class="relative flex h-2 w-2">
                   <span class="animate-ping absolute inline-flex h-full w-full rounded-full bg-green-400 opacity-75"></span>
                   <span class="relative inline-flex rounded-full h-2 w-2 bg-green-500"></span>
                 </span>
                 数据采集任务运行中...
               </p>
             </div>
             <el-date-picker
               v-model="monitorDate"
               type="date"
               placeholder="选择日期"
               size="small"
               class="!w-32 shadow-sm"
               format="MM月DD日"
               value-format="M.D"
               :disabled-date="disabledDate"
               clearable
               @change="handleDateChange"
             >
                <template #default="cell">
                  <div class="h-full w-full flex items-center justify-center rounded-full" :class="getDateCellClass(cell.date)">
                    <span :class="{'font-bold': isValidDate(cell.date)}">{{ cell.text }}</span>
                  </div>
                </template>
             </el-date-picker>
           </div>
           
           <div class="flex-1 flex flex-col gap-3 relative z-10">
             
             <div class="flex gap-3 h-1/2">
               <div class="flex-1 bg-gradient-to-br from-gray-800 to-gray-900 rounded-lg p-4 border border-gray-700 flex flex-col justify-center relative overflow-hidden">
                 <div class="absolute right-0 top-0 p-2 text-gray-700 opacity-20">
                   <el-icon :size="60"><Files /></el-icon>
                 </div>
                 <div class="text-xs text-gray-400 font-bold uppercase tracking-wider mb-1">总文件数 (Total)</div>
                 <div class="text-3xl font-black text-white font-mono tracking-tight">{{ displayStats.total.toLocaleString() }}</div>
                 <div class="mt-2 h-1 w-full bg-gray-700 rounded-full overflow-hidden">
                   <div class="h-full bg-blue-500 w-full"></div>
                 </div>
               </div>

               <div class="w-1/3 bg-gray-800/50 rounded-lg border border-gray-700 flex flex-col items-center justify-center p-2">
                 <el-progress 
                   type="dashboard" 
                   :percentage="qualityPercentage" 
                   :width="80" 
                   :stroke-width="8"
                   :color="qualityColor"
                 >
                   <template #default="{ percentage }">
                     <span class="text-xs text-gray-400 block mb-1">完整率</span>
                     <span class="text-sm font-bold font-mono text-white">{{ percentage }}%</span>
                   </template>
                 </el-progress>
               </div>
             </div>

             <div class="flex gap-3 h-1/2">
                <div class="flex-1 bg-gradient-to-r from-gray-900 to-gray-800/80 border-l-4 border-green-500 rounded-r-lg p-3 flex flex-col justify-center hover:bg-gray-800 transition-colors">
                  <div class="flex justify-between items-start">
                    <div>
                      <div class="text-[10px] text-gray-400 uppercase font-bold">完整文件 (OK)</div>
                      <div class="text-2xl font-bold text-green-400 font-mono mt-1">{{ displayStats.complete.toLocaleString() }}</div>
                    </div>
                    <el-icon class="text-green-500/30"><CircleCheck /></el-icon>
                  </div>
                </div>

                <div class="flex-1 bg-gradient-to-r from-gray-900 to-gray-800/80 border-l-4 border-red-500 rounded-r-lg p-3 flex flex-col justify-center hover:bg-gray-800 transition-colors">
                  <div class="flex justify-between items-start">
                    <div>
                      <div class="text-[10px] text-gray-400 uppercase font-bold">异常文件 (Error)</div>
                      <div class="text-2xl font-bold text-red-400 font-mono mt-1">{{ displayStats.incomplete.toLocaleString() }}</div>
                    </div>
                    <el-icon class="text-red-500/30"><Warning /></el-icon>
                  </div>
                </div>
             </div>

           </div>
        </div>

        <div class="flex flex-col gap-4 h-full">
           <div class="flex-1 bg-[#151923] border border-gray-800 rounded-xl overflow-hidden shadow-xl relative group">
             <div class="absolute top-0 left-0 w-full p-2 bg-gradient-to-b from-black/90 to-transparent z-10 flex justify-between items-center border-b border-white/5">
               <div class="flex items-center gap-2">
                 <span class="w-2 h-2 bg-green-500 rounded-full animate-pulse shadow-[0_0_5px_#22c55e]"></span>
                 <span class="font-bold text-sm text-white">车外影像采集 (Exterior)</span>
               </div>
               <span class="text-[10px] font-mono bg-white/10 px-1.5 py-0.5 rounded text-gray-300">MF168-01</span>
             </div>
             <img src="https://images.unsplash.com/photo-1492144534655-ae79c964c9d7?q=80&w=2000&auto=format&fit=crop" class="w-full h-full object-cover opacity-90 group-hover:scale-105 transition-transform duration-700" />
             <div class="absolute top-1/3 left-1/3 w-32 h-24 border border-yellow-400/80 shadow-[0_0_10px_rgba(250,204,21,0.3)]">
                <div class="absolute -top-5 left-0 text-[10px] bg-yellow-400 text-black px-1 font-bold">车辆 Vehicle 98%</div>
             </div>
           </div>

           <div class="flex-1 bg-[#151923] border border-gray-800 rounded-xl overflow-hidden shadow-xl relative group">
             <div class="absolute top-0 left-0 w-full p-2 bg-gradient-to-b from-black/90 to-transparent z-10 flex justify-between items-center border-b border-white/5">
               <div class="flex items-center gap-2">
                 <span class="w-2 h-2 bg-green-500 rounded-full animate-pulse shadow-[0_0_5px_#22c55e]"></span>
                 <span class="font-bold text-sm text-white">车内驾驶员监控 (Interior)</span>
               </div>
               <span class="text-[10px] font-mono bg-white/10 px-1.5 py-0.5 rounded text-gray-300">MF168-02</span>
             </div>
             <img src="https://images.unsplash.com/photo-1592853625601-bb9d23da126e?q=80&w=2073&auto=format&fit=crop" class="w-full h-full object-cover opacity-90 group-hover:scale-105 transition-transform duration-700" />
             <div class="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-24 h-32 border border-green-500/80 shadow-[0_0_10px_rgba(34,197,94,0.3)]">
               <div class="absolute -top-5 left-0 text-[10px] bg-green-500 text-black px-1 font-bold">驾驶员 Driver</div>
             </div>
           </div>
        </div>

        <div class="bg-[#151923] border border-gray-800 rounded-xl p-5 shadow-xl flex flex-col">
           <div class="flex justify-between items-center mb-4 border-b border-gray-700 pb-3 flex-none">
             <h3 class="text-white font-bold flex items-center gap-2 text-base">
               <el-icon><Compass /></el-icon> IMU 姿态数据
             </h3>
             <el-tag size="small" type="info" effect="dark" class="font-mono">设备: CZ007</el-tag>
           </div>
           
           <div class="flex-1 flex flex-col justify-around">
             <div class="bg-gray-900/30 rounded-lg p-3 border border-gray-700/50">
               <div class="text-[10px] text-gray-400 mb-2 font-bold tracking-wider">加速度计 / ACCELEROMETER (g)</div>
               <div class="space-y-3">
                 <div class="flex items-center gap-2">
                   <span class="w-3 text-xs font-mono text-blue-400 font-bold">X</span>
                   <div class="flex-1 bg-gray-800 h-1.5 rounded-full overflow-hidden relative">
                     <div class="absolute top-0 bottom-0 bg-blue-500 w-1 transition-all duration-100 shadow-[0_0_8px_#3b82f6]" :style="{ left: '50%', transform: `translateX(${imuData.acc.x * 20}px)` }"></div>
                   </div>
                   <span class="w-8 text-right text-xs font-mono text-gray-300">{{ imuData.acc.x.toFixed(2) }}</span>
                 </div>
                 <div class="flex items-center gap-2">
                   <span class="w-3 text-xs font-mono text-blue-400 font-bold">Y</span>
                   <div class="flex-1 bg-gray-800 h-1.5 rounded-full overflow-hidden relative">
                     <div class="absolute top-0 bottom-0 bg-blue-500 w-1 transition-all duration-100 shadow-[0_0_8px_#3b82f6]" :style="{ left: '50%', transform: `translateX(${imuData.acc.y * 20}px)` }"></div>
                   </div>
                   <span class="w-8 text-right text-xs font-mono text-gray-300">{{ imuData.acc.y.toFixed(2) }}</span>
                 </div>
                 <div class="flex items-center gap-2">
                   <span class="w-3 text-xs font-mono text-blue-400 font-bold">Z</span>
                   <div class="flex-1 bg-gray-800 h-1.5 rounded-full overflow-hidden relative">
                     <div class="absolute top-0 bottom-0 bg-blue-500 w-1 transition-all duration-100 shadow-[0_0_8px_#3b82f6]" :style="{ left: '50%', transform: `translateX(${(imuData.acc.z - 9.8) * 20}px)` }"></div>
                   </div>
                   <span class="w-8 text-right text-xs font-mono text-gray-300">{{ imuData.acc.z.toFixed(2) }}</span>
                 </div>
               </div>
             </div>

             <div class="bg-gray-900/30 rounded-lg p-3 border border-gray-700/50">
               <div class="text-[10px] text-gray-400 mb-2 font-bold tracking-wider">陀螺仪 / GYROSCOPE (deg/s)</div>
               <div class="space-y-3">
                 <div class="flex items-center gap-2">
                   <span class="w-3 text-xs font-mono text-orange-400 font-bold">X</span>
                   <div class="flex-1 bg-gray-800 h-1.5 rounded-full overflow-hidden">
                     <div class="h-full bg-orange-500 transition-all duration-100 shadow-[0_0_8px_#f97316]" :style="{ width: Math.abs(imuData.gyro.x) + '%' }"></div>
                   </div>
                   <span class="w-8 text-right text-xs font-mono text-gray-300">{{ imuData.gyro.x.toFixed(1) }}</span>
                 </div>
                 <div class="flex items-center gap-2">
                   <span class="w-3 text-xs font-mono text-orange-400 font-bold">Y</span>
                   <div class="flex-1 bg-gray-800 h-1.5 rounded-full overflow-hidden">
                     <div class="h-full bg-orange-500 transition-all duration-100 shadow-[0_0_8px_#f97316]" :style="{ width: Math.abs(imuData.gyro.y) + '%' }"></div>
                   </div>
                   <span class="w-8 text-right text-xs font-mono text-gray-300">{{ imuData.gyro.y.toFixed(1) }}</span>
                 </div>
                 <div class="flex items-center gap-2">
                   <span class="w-3 text-xs font-mono text-orange-400 font-bold">Z</span>
                   <div class="flex-1 bg-gray-800 h-1.5 rounded-full overflow-hidden">
                     <div class="h-full bg-orange-500 transition-all duration-100 shadow-[0_0_8px_#f97316]" :style="{ width: Math.abs(imuData.gyro.z) + '%' }"></div>
                   </div>
                   <span class="w-8 text-right text-xs font-mono text-gray-300">{{ imuData.gyro.z.toFixed(1) }}</span>
                 </div>
               </div>
             </div>
           </div>
        </div>

      </div>

      <div class="h-[42%] bg-[#151923] border border-gray-800 rounded-xl p-4 shadow-xl flex flex-col">
        <div class="flex justify-between items-center mb-2 flex-none">
          <div class="flex items-center gap-4">
             <h3 class="text-white font-bold flex items-center gap-2 text-base">
               <el-icon class="text-blue-500"><DataLine /></el-icon> 历史数据总览表
             </h3>
             <span class="text-xs text-gray-500">共 {{ historicalData.length }} 条记录</span>
          </div>
          <div class="flex gap-4 text-xs font-mono bg-black/20 px-3 py-1 rounded border border-gray-700">
            <span class="text-gray-400">项目累计总数:</span>
            <span class="text-white font-bold">{{ grandTotals.total.toLocaleString() }}</span>
          </div>
        </div>
        
        <div class="flex-1 overflow-hidden">
          <el-table 
            :data="historicalData" 
            style="width: 100%; height: 100%; background-color: transparent;" 
            :header-cell-style="{background:'#1a1f2e', color:'#9ca3af', borderColor:'#374151', padding:'8px'}"
            :cell-style="{background:'#151923', color:'#e5e7eb', borderColor:'#1f2937', padding:'6px'}"
            size="small"
            stripe
          >
            <el-table-column prop="date" label="采集日期" width="100" sortable>
              <template #default="scope">
                <span class="font-mono text-blue-300 font-bold">{{ scope.row.date }}</span>
              </template>
            </el-table-column>
            
            <el-table-column prop="total" label="总文件数" align="right" width="120">
              <template #default="scope"><span class="font-mono">{{ scope.row.total.toLocaleString() }}</span></template>
            </el-table-column>
            
            <el-table-column prop="complete" label="完整文件" align="right" width="120">
               <template #default="scope"><span class="text-green-400 font-mono">{{ scope.row.complete.toLocaleString() }}</span></template>
            </el-table-column>
            
            <el-table-column prop="incomplete" label="异常文件" align="right" width="120">
               <template #default="scope"><span class="text-red-400 font-mono">{{ scope.row.incomplete }}</span></template>
            </el-table-column>

            <el-table-column label="有效率" width="180">
               <template #default="scope">
                 <el-progress 
                   :percentage="Math.round(scope.row.ratio * 100)" 
                   :color="scope.row.ratio > 0.95 ? '#22c55e' : '#f97316'"
                   :stroke-width="6"
                 />
               </template>
            </el-table-column>
            
            <el-table-column prop="collectors" label="数据采集责任人" show-overflow-tooltip>
               <template #default="scope">
                 <div class="flex flex-wrap gap-1">
                   <el-tag v-for="name in scope.row.collectors.split(/，|,/)" :key="name" size="small" type="info" effect="plain" class="!bg-gray-800 !border-gray-700 !px-1.5 !h-5 !text-[10px]">
                     {{ name.trim() }}
                   </el-tag>
                 </div>
               </template>
            </el-table-column>
          </el-table>
        </div>
      </div>

    </main>
  </div>
</template>

<style>
/* 自定义细滚动条 */
.custom-scrollbar::-webkit-scrollbar { width: 4px; }
.custom-scrollbar::-webkit-scrollbar-track { background: #111827; }
.custom-scrollbar::-webkit-scrollbar-thumb { background: #374151; border-radius: 2px; }

/* 表格样式微调 */
.el-table--enable-row-hover .el-table__body tr:hover > td.el-table__cell {
  background-color: #1f2937 !important;
}
.el-table::before { height: 0; }
</style>