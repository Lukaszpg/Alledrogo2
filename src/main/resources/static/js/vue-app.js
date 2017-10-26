window.onload = function () {
    Vue.component('version', {
        data: function () {
            return {
                version: '',
                message: '',
                compilationDate: ''
            }
        },
        template: '<div id="version" class="right tooltipped hide-on-med-and-down">{{version}}</div>',

        mounted() {
            var vm = this;
            axios.get('/version')
                .then(function (response) {
                    vm.version = response.data.version;
                    vm.message = response.data.message;

                    $('#version').tooltip({
                        delay: 50,
                        tooltip: response.data.message + '<br/>' + response.data.compilationDate,
                        position: 'top',
                        html: true
                    });
                })
                .catch(function () {
                    this.errors.push(e)
                })
        }
    });

    Vue.component('category-tree', {
        data: function () {
            return {
                topCategories: '',
            }
        },
        template:
        '<div id="categoryTree" class="row">' +
            '<div class="col s3 offset-s4">' +
                '<ul class="collapsible" data-collapsible="accordion">' +
                    '<li v-for="category in topCategories">' +
                        '<div class="collapsible-header"><i class="material-icons">filter_drama</i>{{ category.name }}</div>' +
                        '<div class="collapsible-body">' +
                            '<ul v-if="category.children !== null" class="collapsible" data-collapsible="accordion">' +
        '                       <li v-for="child in category.children">' +
                                    '<div class="collapsible-header"><i class="material-icons">filter_drama</i>{{ child.name }}</div>' +
                                    '<div class="collapsible-body"></div>' +
                                '</li>' +
                            '</ul>' +
                        '</div>' +
                    '</li>' +
                '</ul>' +
            '</div>' +
        '</div>',

        mounted() {
            var vm = this;
            axios.get('/category-rest/get-all')
                .then(function (response) {
                    vm.topCategories = response.data;
                    $(".collapsible").collapsible();
                })
                .catch(function () {
                    this.errors.push(e)
                })
        },

        methods: {

        }
    });

    var app = new Vue({
        el: '#app',
    });
}