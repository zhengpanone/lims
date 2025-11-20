import {  createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from './router/'
import { initStores } from './store/'
import i18n from './lang/index'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// 加载全局样式,只能加载非变量、mixin
import './styles/index.scss'


const app = createApp(App);

// 配置 pinia-tore
await initStores(app);

app.use(router).use(ElementPlus).use(i18n);


const modules: Record<string, any> = import.meta.glob(
  './components/**/index.ts',
  { eager: true }
)
for (let path in modules) {
  app.use(modules[path].default)
}

app.mount('#app')

// 导出应用实例（用于调试）
export default app
