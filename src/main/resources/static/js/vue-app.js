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
        template:
        '<div class="category-picker">' +
            '<div class="row">' +
                '<div v-if="parentName != null">{{ parentName }}' +
                '   <a v-if="shouldShowReturn" class = "category right" @click="goToPrevious">Powrót</a>' +
                '</div>' +
                '<hr v-if="parentName != null">' +
                '<div class="col offset-s5">' +
                    '<div v-bind:class="{ \'preloader-wrapper\': true, small: true, active: true, hide: !isLoading, \'category-picker-preloader\': true}">' +
                        '<div class="spinner-layer spinner-yellow-only">' +
                            '<div class="circle-clipper left">' +
                                '<div class="circle"></div>' +
                            '</div>' +
                            '<div class="gap-patch">' +
                                '<div class="circle"></div>' +
                            '</div>' +
                            '<div class="circle-clipper right">' +
                                '<div class="circle"></div>' +
                            '</div>' +
                        '</div>' +
                    '</div>' +
                '</div>' +
            '</div>' +
            '<ul v-bind:class="{ \'category-picker-list\': true, hide: isLoading }">' +
                '<li v-for="category in categories">' +
                    '<div class = "category" v-bind:class="{leaf: category.leaf}" @click="getChildren(category.id, category.leaf)">{{ category.name }}</div>' +
                '</li>' +
            '</ul>' +
        '</div>',

        mounted() {
            var vm = this;
            vm.getTop();
        },

        data() {
            return {
                categories: [],
                parentName: null,
                previousId: 0,
                shouldShowReturn: false,
                isLoading: false
            };
        },

        methods: {
            getChildren: function (id, isLeaf) {
                var vm = this;
                if (!isLeaf) {
                    vm.isLoading = true;
                    axios.get('/category-rest/get-children/' + id)
                        .then(function (response) {
                            vm.categories = response.data;
                            vm.parentName = vm.categories[0].parentName != null ? vm.categories[0].parentName : null;
                            vm.previousId = vm.categories[0].parentOfParentId != null ? vm.categories[0].parentOfParentId : 0;
                            vm.shouldShowReturn = true;
                            vm.isLoading = false;
                        })
                        .catch(function () {
                            this.errors.push(e);
                            console.log(e);
                        })
                } else {

                }
            },
            goToPrevious: function () {
                var vm = this;
                if (vm.previousId == 0) {
                    vm.getTop();
                    vm.shouldShowReturn = false;
                } else {
                    vm.getChildren(vm.previousId, false);
                }
            },
            getTop: function () {
                var vm = this;
                vm.isLoading = true;
                axios.get('/category-rest/get-all-top')
                    .then(function (response) {
                        vm.parentName = null;
                        vm.categories = response.data;
                        vm.isLoading = false;
                    })
                    .catch(function () {
                        this.errors.push(e);
                        console.log(e);
                    })
            }

        }
    });

    var app = new Vue({
        el: '#app',
    });
}