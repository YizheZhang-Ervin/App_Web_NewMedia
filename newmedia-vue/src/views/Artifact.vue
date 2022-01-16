<template>
	<el-card>
		<div slot="header">
			<span>作品集</span>
			<el-button style="float: right; padding: 3px 0" type="text" @click="handlePageChange(1)">刷新
			</el-button>
		</div>
		<el-row type="flex" justify="center" :style="current.style">
			<el-col :span="22">
				<el-timeline>
					<el-timeline-item :timestamp="data.date" placement="top"
						v-for="data in current.arifactData" :key="data.name">
						<el-card>
							<el-descriptions title="作品信息">
								<el-descriptions-item :label="k" v-for="(v,k) in data" :key="v">
									<el-tag size="small" class="tag">{{v}}</el-tag>
								</el-descriptions-item>
								<template slot="extra">
									<el-link :href="data.link" target="_blank" type="success">链接</el-link>
								</template>
							</el-descriptions>
						</el-card>
					</el-timeline-item>
				</el-timeline>
				<el-pagination layout="prev, pager, next" :total="page.total"
					@current-change="handlePageChange">
				</el-pagination>
			</el-col>
		</el-row>
	</el-card>
</template>

<script>
	import { getArtifact } from "@/api/artifact.js"
	export default {
		name: 'Artifact',
		components: {
		},
		data() {
			return {
				current: {
					activeNames: "",
					mediaType: this.$route.params.type,
					arifactData: [
						// {
						// 	name: "1",
						// 	description: "11",
						// 	link: "111",
						// 	date: "1111",
						// 	type: 'film'
						// }
					],
					style: {
						height: parseInt(window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight) - 60 + "px",
					}
				},
				page: {
					total: 1,
					current: 1,
					size: 10
				}
			}
		},
		watch: {
			newMediaType(newVal) {
				this.current.mediaType = newVal
				this.handlePageChange(1)
			}
		},
		computed: {
			newMediaType() {
				return this.$route.params.type
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
				let req = { current: currentPage, size: 10, condition: this.current.mediaType }
				getArtifact(req).then(res => {
					this.current.arifactData = res.data.list
					this.page.total = res.data.total
				})
			}
		}
	}
</script>
<style scoped>
	.tag {
		overflow: hidden;
		text-overflow: ellipsis;
		max-width: 200px;
	}
</style>