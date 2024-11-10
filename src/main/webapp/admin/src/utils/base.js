const base = {
    get() {
        return {
            url : "http://localhost:8080/jiaxiaoxinxi/",
            name: "jiaxiaoxinxi",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/jiaxiaoxinxi/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "驾校信息管理系统"
        } 
    }
}
export default base
