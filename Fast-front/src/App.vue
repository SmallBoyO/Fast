<template>
<div class="container" id="app">
	  <router-link to="/login">login</router-link>
	  <router-link to="/menu2">menu2</router-link>
	  <router-link to="/navi">navi</router-link>
	  <router-link to="/home/menu1">menu1</router-link>
	  <router-link to="/home/test">test</router-link>
	  <router-link to="/home">home</router-link>
	  <transition name="fade">
      <router-view></router-view>
	  </transition>
</div>
</template>
<script>
export default {
	data() {
            return {}
        }
}
</script>

<style>
body {
  font-family: Helvetica, sans-serif;
}
</style>
