<template>
<div class="container" id="app">
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
