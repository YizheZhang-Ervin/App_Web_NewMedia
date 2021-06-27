var app = new Vue({
    el: '#app',
    data() {
        return {
            mouseX: 0,
            mouseY: 0,
            currentEle: "",
            input001:"",
            pics:[
                "./resources/sky.jpg",
                "./resources/colin.jpg",
                "./resources/ervin.jpg",
                "./resources/clj.jpg",
                "./resources/lqj.jpg"
            ]
        }
    },
    mounted() {
        // title时钟
        setInterval(() => {
            this.checkVisibility();
        }, 1000);
        // 获取地理位置
        this.getGeolocation();
        // 防止f12
        this.attack_kp();
        // 防止鼠标右键
        // this.attack_cm();
        // 鼠标移动
        document.onmousemove = this.mouseMove;
    },
    methods: {
        // 更改显示内容
        changePage(key) {
            switch (key) {
                case "1":
                    console.log(key);
                    break;
            }
        },
        // title时钟，当页面在前台可见时
        checkVisibility: function () {
            let vs = document.visibilityState;
            let date = new Date(Date.now());
            if (vs == "visible") {
                document.title =
                    "NIA7 - " +
                    date.getHours() +
                    ":" +
                    date.getMinutes() +
                    ":" +
                    date.getSeconds();
            }
        },
        // 获取鼠标位置
        mouseMove(ev) {
            ev = ev || window.event;
            var mousePos = this.mouseCoords(ev);
            //获取当前的x,y坐标
            this.mouseX = mousePos.x;
            this.mouseY = mousePos.y;
            // 获取当前位置的元素
            let ele = document.elementFromPoint(this.mouseX, this.mouseY);
            this.currentEle = ele;
        },
        mouseCoords(ev) {
            //鼠标移动的位置
            if (ev.pageX || ev.pageY) {
                return { x: ev.pageX, y: ev.pageY };
            }
            return {
                x: ev.clientX + document.body.scrollLeft - document.body.clientLeft,
                y: ev.clientY + document.body.scrollTop - document.body.clientTop,
            };
        },
        // 地理位置
        getGeolocation() {
            navigator.geolocation.getCurrentPosition(this.sendNotification);
        },
        sendNotification(position) {
            // get geolocation
            let latitude =
                position.coords.latitude > 0
                    ? position.coords.latitude + " N"
                    : position.coords.latitude + " S";
            let longitude =
                position.coords.longitude > 0
                    ? position.coords.longitude + " E"
                    : position.coords.longitude + " W";
            // Notification
            var n = new Notification("你当前所在位置为", {
                body: `${latitude},${longitude}`,
                tag: "backup",
                requireInteraction: false,
                data: {
                    loc: `${latitude},${longitude}`,
                },
            });
            n.onclick = function () {
                n.close();
            };
        },
        // 防止鼠标右键
        attack_cm() {
            document.oncontextmenu = function (e) {
                e.preventDefault();
                alert("prevent right click");
            }
        },
        // 防止f12
        attack_kp() {
            document.addEventListener("keydown", (e) => {
                if (e.key == "F12") {
                    window.event.returnValue = false;
                    alert("prevent F12")
                }
            })
        }
    }
});