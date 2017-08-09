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
				  @click="">搜索</el-button>
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
  </section>
</template>
<script type="text/ecmascript-6">
export default {
        data() {
			return {
				tableData:[
					{
					  userName: '2016-05-03',
					  name: '王小虎',
					  status: '上海市普陀区金沙江路 1518 弄'
					},
					{
					  userName: '2016-05-032',
					  name: '王小虎2',
					  status: '上海市普陀区金沙江路 1518 弄2'
					},
					{
					  userName: '2016-05-032',
					  name: '王小虎2',
					  status: '上海市普陀区金沙江路 1518 弄2'
					},
					{
					  userName: '2016-05-032',
					  name: '王小虎2',
					  status: '上海市普陀区金沙江路 1518 弄2'
					},
					{
					  userName: '2016-05-032',
					  name: '王小虎2',
					  status: '上海市普陀区金沙江路 1518 弄2'
					},
					{
					  userName: '2016-05-032',
					  name: '王小虎2',
					  status: '上海市普陀区金沙江路 1518 弄2'
					},
					{
					  userName: '2016-05-032',
					  name: '王小虎2',
					  status: '上海市普陀区金沙江路 1518 弄2'
					},
					{
					  userName: '2016-05-032',
					  name: '王小虎2',
					  status: '上海市普陀区金沙江路 1518 弄2'
					},
					{
					  userName: '2016-05-032',
					  name: '王小虎2',
					  status: '上海市普陀区金沙江路 1518 弄2'
					},
					{
					  userName: '2016-05-032',
					  name: '王小虎2',
					  status: '上海市普陀区金沙江路 1518 弄2'
					},
					{
					  userName: '2016-05-032',
					  name: '王小虎2',
					  status: '上海市普陀区金沙江路 1518 弄2'
					},
					{
					  userName: '2016-05-032',
					  name: '王小虎2',
					  status: '上海市普陀区金沙江路 1518 弄2'
					},
					{
					  userName: '2016-05-032',
					  name: '王小虎2',
					  status: '上海市普陀区金沙江路 1518 弄2'
					},
					{
					  userName: '2016-05-032',
					  name: '王小虎2',
					  status: '上海市普陀区金沙江路 1518 弄2'
					}
				],
				multipleSelection: [],
				searchData:{
					name:'',
					status:''
				},
				options:[
					{
						value: '1',
						label: '启用'
					},{
						value: '2',
						label: '禁用'
					}
				]
			}
		},
		methods:{
			handleSelectionChange(val){
				multipleSelection = val;
			},
			handleEdit(index, row) {
				console.log(index, row);
			},
			handleDelete(index, row) {
				console.log(index, row);
			}
		}
	}
</script>