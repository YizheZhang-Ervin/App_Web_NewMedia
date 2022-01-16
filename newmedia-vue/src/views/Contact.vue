<template>
	<div>
		<el-card shadow="hover">
			<div slot="header">
				<span>留言</span>
				<el-button style="float: right; padding: 3px 0" type="text" @click="submitForm">提交
				</el-button>
			</div>
			<el-form label-position="left" label-width="80px" :model="current.form">
				<el-form-item :label="current.msgboardTableCol.row1.label">
					<el-input v-model="current.form.field1"></el-input>
				</el-form-item>
				<el-form-item :label="current.msgboardTableCol.row2.label">
					<el-input v-model="current.form.field2"></el-input>
				</el-form-item>
				<el-form-item :label="current.msgboardTableCol.row3.label">
					<el-input v-model="current.form.field3"></el-input>
				</el-form-item>
				<el-form-item :label="current.msgboardTableCol.row4.label">
					<el-input v-model="current.form.field4"></el-input>
				</el-form-item>
				<el-row type="flex" justify="space-around">
					<el-col :span="8">
						<el-calendar v-model="current.calendarDate">
						</el-calendar>
					</el-col>
					<el-col :span="14">
						<el-card class="box-card" shadow="hover">
							<div slot="header">
								<span>已预约时间</span>
							</div>
							<el-table :data="current.tableData" stripe style="width: 100%">
								<el-table-column prop="name" label="用户名">
								</el-table-column>
								<el-table-column prop="contact" label="联系方式">
								</el-table-column>
								<el-table-column prop="date" label="预约时间">
								</el-table-column>
							</el-table>
							<el-pagination layout="prev, pager, next" :total="page.total"
								@current-change="handlePageChange" :page-size=10>
							</el-pagination>
						</el-card>
					</el-col>
				</el-row>

			</el-form>
		</el-card>
	</div>
</template>

<script>
	import { getMsgboard, addMsgboard } from "../api/msgboard.js"
	import { msgboardTableCol } from "../DataModel/msgboard.js"
	export default {
		name: 'Contact',
		components: {
		},
		data() {
			return {
				current: {
					calendarDate: new Date(),
					form: { field1: "", field2: "", field3: "", field4: new Date().toLocaleDateString() },
					msgboardTableCol: msgboardTableCol,
					tableData: [
						// { name: "1", contact: "1", date: "1" }
					]
				},
				page: {
					total: 1,
					current: 1,
					size: 10
				}
			}
		},
		watch: {
			'current.calendarDate'(newVal) {
				this.current.form.field4 = newVal.toLocaleDateString()
			}
		},
		mounted() {
			this.handlePageChange(1)
		},
		methods: {
			handlePageChange(current) {
				this.page.current = current
				this.getData(this.page.current)
			},
			submitForm() {
				addMsgboard(this.current.form).then(() => {
					this.$message({
						message: `留言成功`,
						type: 'success'
					});
					this.handlePageChange(1)
				}, (err) => {
					this.$message({
						message: `留言失败${err}`,
						type: 'warning'
					});
				})
			},
			clearForm() {
				this.current.form = { field1: "", field2: "", field3: "", field4: new Date().toLocaleDateString() }
			},
			getData(currentPage = 1) {
				let req = { current: currentPage, size: 10 }
				this.clearForm()
				getMsgboard(req).then(res => {
					this.current.tableData = res.data.list
					this.page.total = res.data.total
				})
			}
		}
	}
</script>
<style scoped>
	.box-card {
		height: 50vh;
		overflow-y: auto;
	}
</style>