<template>
	<section class="admin-main">
		<div v-if="current.displayLogin">
			<el-row type="flex" justify="center" :style="current.style">
				<el-col :span="12">
					<el-card class="box-card">
						<div slot="header">
							<span>登录</span>
							<el-button style="float: right; padding: 3px 0" type="text" @click="loginCheck">Login
							</el-button>
						</div>
						<el-form :model="current.form" label-position="left">
							<el-form-item label="用户名">
								<el-input v-model="current.loginForm.name" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="密码">
								<el-input v-model="current.loginForm.password" autocomplete="off" show-password>
								</el-input>
							</el-form-item>
						</el-form>
					</el-card>
				</el-col>
			</el-row>

		</div>
		<div v-if="!current.displayLogin">
			<el-tabs v-model="current.activeName" @tab-click="handlePageChange(1)" type="card">
				<el-tab-pane label="作品集管理" name="artifact">作品集管理</el-tab-pane>
				<el-tab-pane label="用户管理" name="user">用户管理</el-tab-pane>
				<el-tab-pane label="留言板管理" name="msgboard">留言板管理</el-tab-pane>
			</el-tabs>
			<section class="right">
				<el-button-group>
					<el-button type="success" plain icon="el-icon-plus" @click="openAdd">新增</el-button>
					<el-button type="info" plain icon="el-icon-refresh" @click="handlePageChange(1)">刷新
					</el-button>
				</el-button-group>
			</section>
			<br />
			<el-table :data="table.tableData" stripe border>
				<el-table-column :prop="table.tableCol.row0.prop" :label="table.tableCol.row0.label"
					width="180">
				</el-table-column>
				<el-table-column :prop="table.tableCol.row1.prop" :label="table.tableCol.row1.label"
					width="180">
				</el-table-column>
				<el-table-column :prop="table.tableCol.row2.prop" :label="table.tableCol.row2.label"
					width="180">
				</el-table-column>
				<el-table-column :prop="table.tableCol.row3.prop" :label="table.tableCol.row3.label"
					width="180">
				</el-table-column>
				<el-table-column :prop="table.tableCol.row4.prop" :label="table.tableCol.row4.label"
					width="180">
				</el-table-column>
				<el-table-column :prop="table.tableCol.row5.prop" :label="table.tableCol.row5.label"
					width="180">
				</el-table-column>
				<el-table-column label="操作">
					<template slot-scope="scope">
						<el-button type="primary" plain @click="openUpdate(scope.$index, scope.row)">编辑
						</el-button>
						<el-button type="danger" plain @click="handleDelete(scope.$index, scope.row)">删除
						</el-button>
					</template>
				</el-table-column>
			</el-table>
			<el-pagination layout="prev, pager, next" :total="page.total"
				@current-change="handlePageChange">
			</el-pagination>
			<el-drawer title=" 表格CRUD" :visible.sync="current.displayDrawer" :with-header="false">
				<el-card class="box-card" shadow="hover">
					<div slot="header">
						<span>表格CRUD</span>
						<el-button style="float: right; padding: 3px 0" type="text" @click="submitForm">提交
						</el-button>
					</div>
					<el-form :model="current.form" label-position="left">
						<el-form-item :label="table.tableCol.row1.label">
							<el-input v-model="current.form.field1" autocomplete="off"></el-input>
						</el-form-item>
						<el-form-item :label="table.tableCol.row2.label">
							<el-input v-model="current.form.field2" autocomplete="off"></el-input>
						</el-form-item>
						<el-form-item :label="table.tableCol.row3.label">
							<el-input v-model="current.form.field3" autocomplete="off"></el-input>
						</el-form-item>
						<el-form-item :label="table.tableCol.row4.label">
							<el-input v-model="current.form.field4" autocomplete="off"></el-input>
						</el-form-item>
						<el-form-item :label="table.tableCol.row5.label">
							<el-input v-model="current.form.field5" autocomplete="off"></el-input>
						</el-form-item>
					</el-form>
				</el-card>
			</el-drawer>
		</div>
	</section>
</template>

<script>
	import { getArtifact, deleteArtifact, addArtifact, updateArtifact } from "@/api/artifact.js"
	import { getUser, deleteUser, addUser, updateUser, userLoginCheck } from "@/api/user.js"
	import { getMsgboard, deleteMsgboard, addMsgboard, updateMsgboard } from "@/api/msgboard.js"
	import { artifactTableCol } from '@/DataModel/artifact.js';
	import { userTableCol } from '@/DataModel/user.js';
	import { msgboardTableCol } from '@/DataModel/msgboard.js';
	export default {
		name: 'Admin',
		components: {
		},
		data() {
			return {
				allTableCols: {
					userTableCol: userTableCol,
					artifactTableCol: artifactTableCol,
					msgboardTableCol: msgboardTableCol,
				},
				table: {
					tableData: [{
						id: "1",
						name: 'admin1',
						password: 'admin2',
						authority: 'admin3',
						job: "superadmin4",
						description: "superadmin5"
					}],
					tableCol: {
						row0: { label: "ID", prop: "id" },
						row1: { label: '用户名', prop: "name" },
						row2: { label: '密码', prop: "password" },
						row3: { label: '权限', prop: "authority" },
						row4: { label: '职责', prop: "job" },
						row5: { label: '描述', prop: "description" }
					}
				},
				current: {
					activeName: 'user',
					displayDrawer: false,
					row: "",
					form: { field1: "", field2: "", field3: "", field4: "", field5: "" },
					operation: "",
					loginForm: { name: "", password: "" },
					displayLogin: true,
					style: {
						height: parseInt(window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight) - 60 + "px",
						alignItems: "center"
					}
				},
				page: {
					total: 1,
					current: 1,
					size: 10
				}
			}
		},
		mounted() {
		},
		methods: {
			loginCheck() {
				userLoginCheck(this.current.loginForm).then(res => {
					if (res.data.data === true) {
						this.current.displayLogin = false
						this.handlePageChange(1)
					} else {
						this.current.displayLogin = true
						this.$message({
							message: '登陆失败',
							type: 'warning'
						});
					}
				})
			},
			handlePageChange(current) {
				this.page.current = current
				this.handleSearch(this.page.current)
			},
			submitForm() {
				if (this.current.operation === "add") {
					this.handleAdd()
				} else if (this.current.operation === "update") {
					this.handleUpdate()
				}
			},
			handleSearch(currentPage = 1) {
				let req = { current: currentPage, size: 10 }
				this.clearForm()
				if (this.current.activeName === "artifact") {
					getArtifact(req).then(res => {
						this.table.tableCol = this.allTableCols.artifactTableCol
						this.table.tableData = res.data.list
						this.page.total = res.data.total
					})
				} else if (this.current.activeName === "user") {
					getUser(req).then(res => {
						this.table.tableCol = this.allTableCols.userTableCol
						this.table.tableData = res.data.list
						this.page.total = res.data.total
					})
				} else if (this.current.activeName === "msgboard") {
					getMsgboard(req).then(res => {
						this.table.tableCol = this.allTableCols.msgboardTableCol
						this.table.tableData = res.data.list
						this.page.total = res.data.total
					})
				}
			},
			openAdd() {
				this.current.operation = "add"
				this.current.row = ""
				this.current.displayDrawer = true
			},
			handleAdd() {
				this.current.displayDrawer = false
				if (this.current.activeName === "artifact") {
					addArtifact(this.current.form).then(() => {
						this.handlePageChange(1)
					})
				} else if (this.current.activeName === "user") {
					addUser(this.current.form).then(() => {
						this.handlePageChange(1)
					})
				} else if (this.current.activeName === "msgboard") {
					addMsgboard(this.current.form).then(() => {
						this.handlePageChange(1)
					})
				}
			},
			openUpdate(index, row) {
				this.current.operation = "update"
				this.current.row = row
				this.current.displayDrawer = true
				this.processUpdateRowData(row)
			},
			processUpdateRowData(row) {
				this.current.form.field1 = row[this.table.tableCol.row1.prop]
				this.current.form.field2 = row[this.table.tableCol.row2.prop]
				this.current.form.field3 = row[this.table.tableCol.row3.prop]
				this.current.form.field4 = row[this.table.tableCol.row4.prop]
				this.current.form.field5 = row[this.table.tableCol.row5.prop]
			},
			clearForm() {
				this.current.form = { field1: "", field2: "", field3: "", field4: "", field5: "" }
			},
			handleUpdate() {
				let row = this.current.row
				this.current.displayDrawer = false
				if (this.current.activeName === "artifact") {
					updateArtifact(this.current.form, row.id).then(() => {
						this.handlePageChange(1)
					})
				} else if (this.current.activeName === "user") {
					updateUser(this.current.form, row.id).then(() => {
						this.handlePageChange(1)
					})
				} else if (this.current.activeName === "msgboard") {
					updateMsgboard(this.current.form, row.id).then(() => {
						this.handlePageChange(1)
					})
				}
			},
			handleDelete(index, row) {
				if (this.current.activeName === "artifact") {
					deleteArtifact(row).then(() => {
						this.handlePageChange(1)
					})
				} else if (this.current.activeName === "user") {
					deleteUser(row).then(() => {
						this.handlePageChange(1)
					})
				} else if (this.current.activeName === "msgboard") {
					deleteMsgboard(row).then(() => {
						this.handlePageChange(1)
					})
				}
			}
		}
	}
</script>
<style scoped>
	.right {
		text-align: right;
	}
</style>