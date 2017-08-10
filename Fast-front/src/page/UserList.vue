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
				<el-button
				  @click="search">搜索</el-button>
			</el-form>
			
	</el-col>
	<el-table
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
  </section>
</template>
<script type="text/ecmascript-6">
	import axios from 'axios';
	import qs from 'qs';
	axios.defaults.withCredentials=true;
export default {
        data() {
			return {
				dialogFormVisible:false,
				tableData:[],
				multipleSelection: [],
				searchData:{
					name:'',
					status:''
				},
				editData:{
					uerName:'',
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
						{required:true,message:'请输入用户名',trigger: 'blur'},
						{min:5,max:16,message:'用户名长度在5到16之间',trigger: 'blur'}
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
				this.editData.name = row.name;
				this.editData.status = row.status;
				console.log(index, row);
			},
			handleDelete(index, row) {
				console.log(index, row);
			},
			search(){
				axios.post(`http://127.0.0.1:8081/ajax/UserManager/userList`,qs.stringify(this.searchData)).then(res => res.data).then(data => {
					if(data.ret==1){
						this.tableData = data.result;
					}else if(data.ret==-100){
						this.$router.push('/login');
					}
				});
			},
			submitEdit(formName){
			this.$refs[formName].validate((valid) => {
				if(valid){
					alert("修改成功");
				}
			});
			}
		}
	}
</script>