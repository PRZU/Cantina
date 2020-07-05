import '@babel/polyfill'
import 'mutationobserver-shim'
import Vue from 'vue'
import './plugins/bootstrap-vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import About from './components/About.vue'
import MainPage from './components/MainPage.vue'
import PDFReader from './components/PDFReader.vue'

Vue.config.productionTip = false

Vue.use(VueRouter)
const router = new VueRouter({
  routes: [
    { path: '/', component: MainPage },
    { path: '/about', component: About },
    { path: '/menu', component: PDFReader}
  ]
})

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
