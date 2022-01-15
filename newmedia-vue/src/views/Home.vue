<template>
	<div>
		<el-carousel :interval="4000" type="card" :height="height">
			<el-carousel-item v-for="item in current.picLis" :key="item">
				<el-image :src="item" fit="cover">
				</el-image>
			</el-carousel-item>
		</el-carousel>
		<el-row type="flex" justify="space-around">
			<el-col :span="22">
				<el-collapse v-model="current.activeNames">
					<el-collapse-item :title="user.name" :name="user.id" v-for="user in current.userData"
						:key="user.id">
						<el-descriptions title="成员信息" :column="1">
							<el-descriptions-item label="name">
								<el-tag>{{user.name}}</el-tag>
							</el-descriptions-item>
							<el-descriptions-item label="description">
								<el-tag>{{user.description}}</el-tag>
							</el-descriptions-item>
							<template slot="extra">
								<el-tag type="success">{{user.job}}</el-tag>
							</template>
						</el-descriptions>
					</el-collapse-item>
				</el-collapse>
				<el-pagination layout="prev, pager, next" :total="page.total"
					@current-change="handlePageChange">
				</el-pagination>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	import { getUser } from "@/api/user.js"
	import pic from "@/assets/logo.png"
	export default {
		name: 'Home',
		components: {
		},
		data() {
			return {
				current: {
					activeNames: 1,
					userData: [{
						id: "1",
						name: 'admin1',
						password: 'admin2',
						authority: 'admin3',
						job: "superadmin4",
						description: "superadmin5"
					}],
					picLis: [pic]
				},
				page: {
					total: 1,
					current: 1,
					size: 10
				},
				height: parseInt(window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight) / 2 + "px"
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
			getData(currentPage = 1) {
				let req = { current: currentPage, size: 10 }
				getUser(req).then(res => {
					this.current.userData = res.data.list
					this.page.total = res.data.total
				})
			}
		}
	}
</script>
<style>
	.el-collapse-item__header {
		font-size: 1.2em;
	}
</style>