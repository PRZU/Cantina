import '@babel/polyfill'
import 'mutationobserver-shim'
import Vue from 'vue'
import './plugins/bootstrap-vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import About from './components/About.vue'
import MainPage from './components/MainPage.vue'

Vue.config.productionTip = false

Vue.use(VueRouter)

const router = new VueRouter({
  routes: [
    { path:'*', redirect: MainPage},
    { path: '/', component: MainPage },
    { path: '/about', component: About }
  ]
})

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
