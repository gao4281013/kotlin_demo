package com.example.administrator.mykotlin_project.mvp.model

/**
 * Created by Administrator on 2017/7/27 0027.
 */
data class HomeBean(var nextPageUrl: String,
                    var nextPublishTime: Long,
                    var newestIssueType: String,
                    var dialog: Any,
                    var issueList: List<IssueList>) {
    data class IssueList(var releaseTime: Long,
                         var `type`: String,
                         var date: Long,
                         var publishTime: Long,
                         var count: Int,
                         var itemList: List<ItemList>) {
        data class ItemList(var `type`: String,
                            var data: Data,
                            var tag: Any) {
            data class Data(var dataType: String,
                            var id: Int,
                            var title: String,
                            var description: String,
                            var image: String,
                            var actionUrl: String,
                            var adTrack: Any,
                            var shade: Boolean,
                            var label: Any,
                            var labelList: Any,
                            var header: Any)
        }
    }
}