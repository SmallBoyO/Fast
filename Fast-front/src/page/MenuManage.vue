<template>
	<el-tree
	:data="data2"
	:props="defaultProps"
	show-checkbox
	node-key="id"
	default-expand-all
	:expand-on-click-node="false"
	:render-content="renderContent">
	</el-tree>
</template>

<script type="text/ecmascript-6">
let id = 1000;

export default {
	data() {
		return {
			data2: [{
				id: 1,
				label: '一级 1',
				children: [{
					id: 4,
					label: '二级 1-1',
					children: [{
						id: 9,
						label: '三级 1-1-1'
					}, {
						id: 10,
						label: '三级 1-1-2'
					}]
				}]
			}, {
				id: 2,
				label: '一级 2',
				children: [{
					id: 5,
					label: '二级 2-1'
				}, {
					id: 6,
					label: '二级 2-2'
				}]
			}, {
				id: 3,
				label: '一级 3',
				children: [{
					id: 7,
					label: '二级 3-1'
				}, {
					id: 8,
					label: '二级 3-2'
				}]
			}],
			defaultProps: {
				children: 'children',
				label: 'label'
			}
		}
	},

	methods: {
		append(store, data) {
			this.$prompt('请输入菜单标题', '提示2', {
	          confirmButtonText: '确定',
	          cancelButtonText: '取消'
	        }).then(({ value }) => {
						console.log(value);
						store.append({ id: id++, label: value, children: [] }, data);
					})
		},

		remove(store, data) {
			console.log(data);
			store.remove(data);
		},

		renderContent(h, { node, data, store }) {
			return (
				<span>
					<span>
						<span>{node.label}</span>
					</span>
					<span style="float: right; margin-right: 20px">
						<el-button size="mini" on-click={ () => this.append(store, data) }>Append</el-button>
						<el-button size="mini" on-click={ () => this.remove(store, data) }>Delete</el-button>
					</span>
				</span>);
		}
	}
};
</script>
