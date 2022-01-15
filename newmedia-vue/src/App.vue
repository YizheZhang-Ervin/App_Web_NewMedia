<template>
	<div id="app">
		<!-- 页头 -->
		<el-row class="dark-bg" type="flex" justify="end">
			<el-col :span="6" class="flex-center">
				<h1 class="title">
					<i class="el-icon-video-camera-solid"></i>&nbsp;
					新媒体俱乐部NMC
				</h1>
			</el-col>
			<el-col :span="18" class="flex-right">
				<el-menu :default-active="menu.menu1.activeIndex" mode="horizontal"
					background-color="#06396d" text-color="#fff" active-text-color="#ffd04b" :router="true">
					<el-menu-item index="/home">主页</el-menu-item>
					<el-submenu index="/artifact">
						<template slot="title">作品集</template>
						<el-menu-item index="/artifact/microfilm">微电影</el-menu-item>
						<el-menu-item index="/artifact/sitcom">情景剧</el-menu-item>
						<el-menu-item index="/artifact/host">主持 / 演讲 / PPT</el-menu-item>
						<el-menu-item index="/artifact/art">歌 / 舞 / 画 / 乐器</el-menu-item>
						<el-menu-item index="/artifact/web">Web设计 / H5</el-menu-item>
						<el-menu-item index="/artifact/phone">安卓 / 苹果 / 小程序</el-menu-item>
						<el-menu-item index="/artifact/animation">游戏动画</el-menu-item>
					</el-submenu>
					<el-menu-item index="/contact">联系我们</el-menu-item>
					<el-menu-item index="/admin">管理后台</el-menu-item>
				</el-menu>
			</el-col>
		</el-row>
		<!-- 主体 -->
		<section class="flex-between">
			<!-- 侧边栏 -->
			<el-menu :style="mainPartStyle" class="side-bar" :default-active="menu.menu2.activeIndex"
				:collapse="menu.menu2.isCollapse" background-color="#06396d" text-color="#fff"
				active-text-color="#ffd04b" :router="true">
				<el-image :src="picUrl" fit="cover">
				</el-image>
				<el-menu-item index="home">
					<i class="el-icon-s-home"></i>
					<span slot="title">主页</span>
				</el-menu-item>
				<el-submenu index="menu">
					<template slot="title">
						<i class="el-icon-menu"></i>
						<span>作品集</span>
					</template>
					<el-menu-item-group>
						<template slot="title">多媒体技术</template>
						<el-menu-item index="/artifact/microfilm">微电影</el-menu-item>
						<el-menu-item index="/artifact/sitcom">情景剧</el-menu-item>
					</el-menu-item-group>
					<el-menu-item-group title="艺术">
						<el-menu-item index="/artifact/host">主持 / 演讲 / PPT</el-menu-item>
						<el-submenu index="art">
							<template slot="title">歌 / 舞 / 画 / 乐器</template>
							<el-menu-item index="/artifact/art#sing">唱歌</el-menu-item>
							<el-menu-item index="/artifact/art#dance">跳舞</el-menu-item>
							<el-menu-item index="/artifact/art#draw">画图</el-menu-item>
							<el-menu-item index="/artifact/art#instrument">乐器</el-menu-item>
						</el-submenu>
					</el-menu-item-group>
					<el-menu-item-group title="IT技术">
						<el-menu-item index="/artifact/web">Web设计 / H5</el-menu-item>
						<el-menu-item index="/artifact/phone">Android / IOS / 小程序</el-menu-item>
						<el-menu-item index="/artifact/animation">游戏动画</el-menu-item>
					</el-menu-item-group>
				</el-submenu>
				<el-menu-item index="/contact">
					<i class="el-icon-s-promotion"></i>
					<span slot="title">联系我们</span>
				</el-menu-item>
				<el-menu-item index="/admin">
					<i class="el-icon-monitor"></i>
					<span slot="title">管理后台</span>
				</el-menu-item>
				<el-menu-item index="" @click="changeCollapse">
					<i class="el-icon-s-fold"></i>
					<span slot="title">隐藏</span>
				</el-menu-item>
			</el-menu>
			<router-view :style="mainPartStyle" class="main-part"></router-view>
		</section>
	</div>
</template>

<script>
	import pic from "@/assets/logo.png"
	export default {
		name: 'app',
		components: {
		},
		data() {
			return {
				menu: {
					menu1: { activeIndex: "home" },
					menu2: {
						activeIndex: "home",
						isCollapse: true
					}
				},
				mainPartStyle: {
					height: parseInt(window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight) - 60 + "px",
					overflow: "auto"
				},
				picUrl: pic
			}
		},
		methods: {
			changeCollapse() {
				this.menu.menu2.isCollapse = !this.menu.menu2.isCollapse
			}
		}
	}
</script>

<style>
	* {
		margin: 0;
	}
	body {
		overflow: hidden;
	}
	#app {
		font-family: "Avenir", Helvetica, Arial, sans-serif;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
		text-align: center;
		color: #2c3e50;
	}
	.title {
		font-family: Cambria, Cochin, Georgia, Times, "Times New Roman", serif;
	}
	.flex-right {
		display: flex;
		justify-content: flex-end;
	}
	.flex-left {
		display: flex;
		justify-content: flex-start;
	}
	.flex-center {
		display: flex;
		justify-content: center;
		align-items: center;
	}
	.flex-between {
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	.dark-bg {
		background-color: #06396d;
		color: white;
	}
	.side-bar {
		text-align: justify;
	}
	.side-bar:not(.el-menu--collapse) {
		width: 300px;
	}
	.main-part {
		max-width: 100vw;
		width: 95vw;
	}
</style>
