<template>
	<div>
    <h1>vuex</h1>
    Clicked: {{ getCount }} times
	<button @click="countadd">+</button>
	<button @click="countsub">-</button>
  </div>
</template>

<script type="text/ecmascript-6">
	import { mapState } from 'vuex'
	export default {
		computed:mapState({
		getCount:'count'
		}),
		methods:{
			countadd () {
				this.$store.state.count++;
				console.log(this.$store.state.count);
			},countsub () {
				this.$store.state.count--;
				console.log(this.$store.state.count);
			}
		}
	}
</script>