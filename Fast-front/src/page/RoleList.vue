<template>
	<section>
	<!--工具条-->
	<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true">
					<el-form-item>
							<el-input v-model="searchData.role" placeholder="角色"></el-input>
					</el-form-item>
					<el-form-item>
							<el-input v-model="searchData.description" placeholder="描述"></el-input>
					</el-form-item>
					<el-form-item>
							<el-select v-model="searchData.status" placeholder="请选择">
									<el-option
													v-for="item in options"
													:key="item.value"
													:label="item.label"
													:value="item.value">
									</el-option>
							</el-select>
					</el-form-item>
					<el-button @click="search">搜索</el-button>
					<el-button v-if="right.systemroleadd" @click="addRole">添加角色</el-button>
			</el-form>
	</el-col>
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

	<el-dialog title="添加角色" :visible.sync="addRoleVisible">
			<el-form :inline="false"  ref="addRoleDorm" :model="addData" label-width="80px">
					<el-form-item label="角色名" prop="name">
							<el-input placeholder="角色名" v-model="addData.name"></el-input>
					</el-form-item>
					<el-form-item label="描述" prop="name">
							<el-input placeholder="描述" v-model="addData.description"></el-input>
					</el-form-item>
					<el-form-item label="状态">
							<el-select v-model="addData.status" placeholder="请选择">
									<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
									</el-option>
							</el-select>
					</el-form-item>
			</el-form>
	</el-dialog>
	</section>
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
							description:'',
							status: '',
							correntPage:1,
							pageSize:10,
							total:10
					},
					options: [
							{
									value: '1',
									label: '启用'
							}, {
									value: '2',
									label: '禁用'
							}
					],
					addRoleVisible:false,
					addData:{
							name:'',
							status:0,
							description:'',
					}
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
			},
			addRole(){
					this.addRoleVisible = true;

					console.log('addRole');
			}
		},
		created(){
			console.log('-----------');
			this.getRight();
			this.search();
		}
	}
</script>
