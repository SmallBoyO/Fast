<template>
	<el-table
		:data="listdata"
		stripe
		style="width: 100%">
		<el-table-column
			prop="role"
			label="角色"
			width="180">
		</el-table-column>
		<el-table-column
			prop="description"
			label="描述"
			width="180">
		</el-table-column>
		<el-table-column
			prop="status"
			label="状态">
		</el-table-column>
		<el-table-column label="操作">
				<template scope="scope">
						<el-button v-if="right.systemroleedit" size="small" @click="handleEdit(scope.$index, scope.row)">
						编辑
						</el-button>
				</template>
		</el-table-column>
	</el-table>
</template>

<script type="text/ecmascript-6">
	import axios from 'axios';
	import qs from 'qs';
	axios.defaults.withCredentials=true;
	export default {
		data() {
			return {
					right:{},
					listdata:[{id:1,role:'admin',description:'admin',status:1}],
					searchData: {
							role: '',
							status: '',
							correntPage:1,
							pageSize:10,
							total:10
					},
			}
		},
		methods:{
			getRight(){
				axios.post(`http://127.0.0.1:8081/ajax/getRight`,qs.stringify({id:this.$route.params.rightid})).then(res => res.data).then(data => {
						console.log(data);
						this.right = data;
				});
			},
			handleEdit(index, row) {
					console.log(index, row);
			},
			search(){
				axios.post(`http://127.0.0.1:8081/ajax/roleManager/roleList`,qs.stringify(this.searchData)).then(res => res.data).then(data => {
						console.log(data);
						this.listdata=data.result;
						this.searchData.correntPage = data.correntPage;
						this.searchData.pageSize = data.pageSize;
						this.searchData.total = data.total;
				});
			}
		},
		created(){
			console.log('-----------');
			this.getRight();
			this.search();
		}
	}
</script>
