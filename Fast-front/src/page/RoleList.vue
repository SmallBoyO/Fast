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
	<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
								 :current-page="searchData.correntPage" :page-sizes="[10, 20, 50, 100, 200]" :page-size="searchData.pageSize"
								 layout="total, sizes, prev, pager, next, jumper" :total="searchData.total">
	</el-pagination>
	<el-dialog v-if="right.systemroleadd" title="添加角色" size='tiny' :visible.sync="addRoleVisible">
			<el-form :inline="false" :rules="addrules" ref="addRoleForm" :model="addData" label-width="80px">
					<el-form-item label="角色名" prop="role">
							<el-input placeholder="角色名" v-model="addData.role"></el-input>
					</el-form-item>
					<el-form-item label="描述" prop="description">
							<el-input placeholder="描述" v-model="addData.description"></el-input>
					</el-form-item>
					<el-form-item label="状态">
							<el-select v-model="addData.status" placeholder="请选择">
									<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
									</el-option>
							</el-select>
					</el-form-item>
					<el-form-item label="权限">
						<el-tree
						  :data="rightlist"
						  show-checkbox
						  default-expand-all
						  node-key="id"
						  ref="tree"
						  highlight-current
							@check-change = "changeCheck"
						  :props="defaultProps">
						</el-tree>
					</el-form-item>
					<el-form-item>
							<el-button size="small" type="danger" @click="submitAdd('addRoleForm')">添加</el-button>
					</el-form-item>
			</el-form>
	</el-dialog>

	<el-dialog v-if="right.systemroleedit" title="编辑角色" size='tiny' :visible.sync="editRoleVisible">
			<el-form :inline="false" :rules="editrules" ref="editRoleForm" :model="editData" label-width="80px">
					<el-form-item label="角色名" prop="role">
							<el-input placeholder="角色名" v-model="editData.role"></el-input>
					</el-form-item>
					<el-form-item label="描述" prop="description">
							<el-input placeholder="描述" v-model="editData.description"></el-input>
					</el-form-item>
					<el-form-item label="状态">
							<el-select v-model="editData.status" placeholder="请选择">
									<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
									</el-option>
							</el-select>
					</el-form-item>
					<el-form-item label="权限">
						<el-tree
						  :data="rightlist"
						  show-checkbox
						  default-expand-all
						  node-key="id"
						  ref="edittree"
						  highlight-current
							@check-change = "changeeditCheck"
						  :props="defaultProps">
						</el-tree>
					</el-form-item>
					<el-form-item>
							<el-button size="small" type="danger" @click="submitEdit('editRoleForm')">提交</el-button>
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
			var validateAddRole = (rule, value, callback) => {
				if (value == '') {
						callback(new Error('请输入角色名！'));
				} else if (value.length < 5 || value.length > 16) {
						callback(new Error('角色名长度在4到16之间！'));
				}else{
					axios.post(`http://127.0.0.1:8081/ajax/roleManager/checkRoleName`, qs.stringify({role: value})).then(res => res.data).then(data => {
							console.log(data);
							if (data.ret == -1) {
									callback(new Error(data.message));
							} else {
									callback();
							}
					});
				}
			};
			var validateEditRole = (rule, value, callback) => {
				if (value == '') {
						callback(new Error('请输入角色名！'));
				} else if (value.length < 4 || value.length > 16) {
						callback(new Error('角色名长度在4到16之间！'));
				}else{
					axios.post(`http://127.0.0.1:8081/ajax/roleManager/checkRoleName`, qs.stringify({role: value,id:this.editData.id})).then(res => res.data).then(data => {
							console.log(data);
							if (data.ret == -1) {
									callback(new Error(data.message));
							} else {
									callback();
							}
					});
				}
			};
			var validateAdddescription = (rule, value, callback) => {
				if ( value.length > 64) {
						callback(new Error('描述长度必须小于64！'));
				}else{
						callback();
				}
			};
			return {
					addrules:{
							role:[
									{validator: validateAddRole, trigger: 'blur'}
							],
							description:[
									{validator: validateAdddescription, trigger: 'blur'}
							]
					},
					editrules:{
							role:[
									{validator: validateEditRole, trigger: 'blur'}
							],
							description:[
									{validator: validateAdddescription, trigger: 'blur'}
							]
					},
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
					editRoleVisible:false,
					addData:{
							role:'',
							status:0,
							description:'',
							rightlist:[]
					},
					editData:{
							id:0,
							role:'',
							status:0,
							description:'',
							rightlist:[]
					},
					rightlist: [],
					defaultProps: {
	          children: 'child',
	          label: 'name'
	        }
			}
		},
		methods:{
			handleSizeChange(val) {
					this.searchData.pageSize = val;
					console.log(`每页 ${val} 条`);
					this.search();
					console.log(this.searchData.pageSize);
			},
			handleCurrentChange(val) {
					this.searchData.correntPage = val;
					this.search();
					console.log(`当前页: ${val}`);
					console.log(this.searchData.correntPage);
			},
			getRight(){
				axios.post(`http://127.0.0.1:8081/ajax/getRight`,qs.stringify({id:this.$route.params.rightid})).then(res => res.data).then(data => {
						console.log(data);
						this.right = data;
				});
				axios.post(`http://127.0.0.1:8081/ajax/getAllRight`,qs.stringify({})).then(res => res.data).then(data => {
						console.log(data);
						this.rightlist = data;
				});
			},
			handleEdit(index, row) {
					this.editRoleVisible=true;
					this.editData.id = row.id;
					this.editData.role = row.role;
					this.editData.description = row.description;
					this.editData.status = row.status;
					axios.post(`http://127.0.0.1:8081/ajax/roleManager/getRolePermission`,qs.stringify({roleId:row.id})).then(res => res.data).then(data => {
							console.log(data);
							this.$refs.edittree.setCheckedKeys(data.obj);
					});
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
			},
			changeCheck(sd,sds,s){
				this.addData.rightlist = this.$refs.tree.getCheckedKeys();
			},
			changeeditCheck(sd,sds,s){
				this.editData.rightlist = this.$refs.edittree.getCheckedKeys();
			},
			submitAdd(formName){
				this.$refs[formName].validate((valid) => {
						if(valid){
							console.log(this.addData);
							let data={
									role:this.addData.role,
									description:this.addData.description,
									status:this.addData.status,
							}
							let rightstr='';
							for(let right in this.addData.rightlist){
								rightstr+=('&'+'rightlist='+this.addData.rightlist[right]);
							}
							console.log(rightstr);
							axios.post(`http://127.0.0.1:8081/ajax/roleManager/addRole`,qs.stringify(data)+rightstr).then(res => res.data).then(data => {
									console.log(data);
									if(data.ret==1){
											this.addRoleVisible = false;
											this.$alert(data.message, '', {
													confirmButtonText: '确定'
											});
											this.search();
									}else if (data.ret == -100) {
											this.$store.commit('setMenu',null);
											this.$router.push('/login');
									} else {
											this.$alert(data.message, '', {
													confirmButtonText: '确定'
											});
									}
							});
						}
				 });
			},
			submitEdit(formName){
				this.$refs[formName].validate((valid) => {
						if(valid){
							let data={
									roleId:this.editData.id,
									role:this.editData.role,
									description:this.editData.description,
									status:this.editData.status,
							}
							let rightstr='';
							for(let right in this.editData.rightlist){
								rightstr+=('&'+'rightlist='+this.editData.rightlist[right]);
							}
							console.log(this.editData);
							axios.post(`http://127.0.0.1:8081/ajax/roleManager/editRole`,qs.stringify(data)+rightstr).then(res => res.data).then(data => {
									console.log(data);
									if(data.ret==1){
											this.editRoleVisible = false;
											this.$alert(data.message, '', {
													confirmButtonText: '确定'
											});
											this.search();
									}else if (data.ret == -100) {
											this.$store.commit('setMenu',null);
											this.$router.push('/login');
									} else {
											this.$alert(data.message, '', {
													confirmButtonText: '确定'
											});
									}
							});
						}
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
