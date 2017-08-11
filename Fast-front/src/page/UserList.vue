<template>
	<section>
	<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="searchData">
				<el-form-item>
					<el-input  placeholder="姓名" v-model="searchData.name"></el-input>
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
				<el-button @click="addUser">添加用户</el-button>
			</el-form>
			
	</el-col>
	<el-table
	v-loading="listLoading"
    :data="tableData"
    height="700"
    border
    style="width: 100%"
	@selection-change="handleSelectionChange"
	>
	<el-table-column
      type="selection"
      width="55">
    </el-table-column>
    <el-table-column
      prop="userName"
      label="账号"
      width="180">
    </el-table-column>
    <el-table-column
      prop="name"
      label="用户名"
      width="180">
    </el-table-column>
    <el-table-column
      prop="status"
      label="状态">
    </el-table-column>
	<el-table-column label="操作">
      <template scope="scope">
        <el-button
          size="small"
          @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
        <el-button
          size="small"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  
   <el-dialog title="编辑用户" size="tiny" :visible.sync="dialogFormVisible">
		<el-form :inline="false" :rules="rules" ref="editform" :model="editData" label-width="80px">
			<el-form-item label="用户名" prop="name">
					<el-input  placeholder="用户名" v-model="editData.name"></el-input>
			</el-form-item>
			<el-form-item label="状态">
					<el-select v-model="editData.status" placeholder="请选择">
						<el-option
						  v-for="item in options"
						  :key="item.value"
						  :label="item.label"
						  :value="item.value">
						</el-option>
					 </el-select>
			</el-form-item>
			<el-form-item>
			<el-button size="small" type="danger" @click="submitEdit('editform')">提交修改</el-button>
			</el-form-item>
		</el-form>
   </el-dialog>
   
   <el-dialog title="添加用户" size="tiny" :visible.sync="dialogaddFormVisible">
		<el-form :inline="false" :rules="addrules" ref="addform" :model="addData" label-width="80px">
			<el-form-item label="账号" prop="userName">
					<el-input  placeholder="账号" v-model="addData.userName"></el-input>
			</el-form-item>
			<el-form-item label="用户名" prop="name">
					<el-input  placeholder="用户名" v-model="addData.name"></el-input>
			</el-form-item>
			<el-form-item label="密码" prop="checkPass">
					<el-input   placeholder="密码" v-model="addData.password"></el-input>
			</el-form-item>
			<el-form-item label="密码确认" prop="checkAgain">
					<el-input   placeholder="请再次输入密码" v-model="addData.password2"></el-input>
			</el-form-item>
			<el-form-item label="状态">
					<el-select v-model="addData.status" placeholder="请选择">
						<el-option
						  v-for="item in options"
						  :key="item.value"
						  :label="item.label"
						  :value="item.value">
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
			var validateUserName = (rule, value, callback) => {
				if (value == '') {
					callback(new Error('请输入用户名！'));
				}else if (value.length <5 || value.length>16){
					callback(new Error('用户名长度在5到16之间！'));
				}else{
					axios.post(`http://127.0.0.1:8081/ajax/UserManager/checkUserName`,qs.stringify({userName : value})).then(res => res.data).then(data => {
						console.log(data);
						if(data.ret == -1){
							callback(new Error(data.message));
						}else{
							callback();
						}
					});
				}
				
			};
			var validateName = (rule, value, callback) => {
				if (value == '') {
					callback(new Error('请输入用户名！'));
				}else if (value.length <5 || value.length>16){
					callback(new Error('用户名长度在5到16之间！'));
				}else{
					axios.post(`http://127.0.0.1:8081/ajax/UserManager/checkName`,qs.stringify({name : value,id : this.editData.id})).then(res => res.data).then(data => {
						console.log(data);
						if(data.ret == -1){
							callback(new Error(data.message));
						}else{
							callback();
						}
					});
				}
				
			};
			var validateAddName = (rule, value, callback) => {
				if (value == '') {
					callback(new Error('请输入用户名！'));
				}else if (value.length <5 || value.length>16){
					callback(new Error('用户名长度在5到16之间！'));
				}else{
					axios.post(`http://127.0.0.1:8081/ajax/UserManager/checkName`,qs.stringify({name : value})).then(res => res.data).then(data => {
						console.log(data);
						if(data.ret == -1){
							callback(new Error(data.message));
						}else{
							callback();
						}
					});
				}
				
			};
			var validatePass = (rule, value, callback) => {
					if (value === '') {
					  callback(new Error('请输入密码'));
					}else if(value.length < 8){
					  callback(new Error('密码长度不能小于8'));
					} else {
					  if (this.addform.checkPass2 !== '') {
						this.$refs.addform.validateField('checkPass2');
					  }
					  callback();
					}
			};
			var validatePass2 = (rule, value, callback) => {
				if (value === '') {
				  callback(new Error('请输入密码'));
				}else if(value !== this.addData.password){
				  callback(new Error('两次填写的密码必须一致'));
				} else {
				  callback();
				}
			};
			return {
				dialogFormVisible:false,
				dialogaddFormVisible:false,
				listLoading:false,
				tableData:[],
				multipleSelection: [],
				searchData:{
					name:'',
					status:''
				},
				addData:{
					name:'',
					userName:'',
					password:'',
					password2:'',
					status:1
				},
				editData:{
					id:0,
					userName:'',
					password:'',
					name:'',
					status:1
				},
				options:[
					{
						value: '1',
						label: '启用'
					},{
						value: '2',
						label: '禁用'
					}
				],
				rules:{
					name:[
						{validator:validateName,trigger: 'blur'}
					]
				},
				addrules:{
					name:[
						{validator:validateAddName,trigger: 'blur'}
					],
					userName:[
						{validator:validateUserName,trigger:'blur'}
					],
					checkAgain:[
						{validator:validatePass2,trigger:'blur'}
					],
					checkPass:[
						{validator:validatePass,trigger:'blur'}
					]
				}
			}
		},
		methods:{
			handleSelectionChange(val){
				multipleSelection = val;
			},
			handleEdit(index, row) {
				this.dialogFormVisible=true;
				this.editData.id = row.id;
				this.editData.name = row.name;
				this.editData.status = row.status;
				console.log(index, row);
			},
			handleDelete(index, row) {
				console.log(index, row);
			},
			search(){
				this.listLoading = true;
				axios.post(`http://127.0.0.1:8081/ajax/UserManager/userList`,qs.stringify(this.searchData)).then(res => res.data).then(data => {
					if(data.ret==1){
						this.tableData = data.result;
						this.listLoading = false;
					}else if(data.ret==-100){
						this.listLoading = false;
						this.$router.push('/login');
					}
				});
			},
			addUser(){
				this.dialogaddFormVisible = true;
			},
			submitEdit(formName){
				this.$refs[formName].validate((valid) => {
					if(valid){
						axios.post(`http://127.0.0.1:8081/ajax/UserManager/updateUser`,qs.stringify(this.editData)).then(res => res.data).then(data => {
						if(data.ret==1){
							this.$alert(data.message, '', {
							  confirmButtonText: '确定'
							});
							this.dialogFormVisible=false;
							this.editData={
								uerName:'',
								password:'',
								name:'',
								status:1
							};
							this.search();
						}else if(data.ret==-99){
							this.$alert(data.message, '', {
							  confirmButtonText: '确定'
							});
							this.dialogFormVisible=false;
						}else if(data.ret==-100){
							this.$router.push('/login');
						}else{
							this.$alert(data.message, '', {
							  confirmButtonText: '确定'
							});
						}
					});
					}
				});
			}
		}
	}
</script>