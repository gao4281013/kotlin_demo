package com.example.administrator.mykotlin_project.mvp.model

/**
 * Created by Administrator on 2017/8/1 0001.
 */
data class HotBean(var count: Int,
                   var total: Int,
                   var nextPageUrl: Any?,
                   var itemList: List<ItemList>) {
    data class ItemList(var `type`: String?,
                        var data: Data?,
                        var tag: Any?) {
        data class Data(var dataType: String?,
                        var id: Int,
                        var title: String?,
                        var slogan: String?,
                        var description: String?,
                        var provider: Provider?,
                        var category: String?,
                        var author: Author?,
                        var cover: Cover?,
                        var playUrl: String?,
                        var thumbPlayUrl: String?,
                        var duration: Int,
                        var webUrl: WebUrl?,
                        var releaseTime: Long,
                        var library: String?,
                        var consumption: Consumption?,
                        var campaign: Any?,
                        var waterMarks: Any?,
                        var adTrack: Any?,
                        var `type`: String?,
                        var titlePgc: String?,
                        var descriptionPgc: String?,
                        var remark: Any?,
                        var idx: Int,
                        var shareAdTrack: Any?,
                        var favoriteAdTrack: Any?,
                        var webAdTrack: Any?,
                        var date: Long,
                        var promotion: Any?,
                        var label: Any?,
                        var descriptionEditor: String?,
                        var collected: Boolean,
                        var played: Boolean,
                        var lastViewTime: Any?,
                        var playInfo: List<PlayInfo>?,
                        var tags: List<Tags>?,
                        var labelList: List<*>?,
                        var subtitles: List<*>?) {
            data class Provider(var name: String?,
                                var alias: String?,
                                var icon: String?)

            data class Author(var id: Int,
                              var icon: String?,
                              var name: String?,
                              var description: String?,
                              var link: String?,
                              var latestReleaseTime: Long,
                              var videoNum: Int,
                              var adTrack: Any?,
                              var follow: Follow?,
                              var shield: Shield?,
                              var approvedNotReadyVideoCount: Int) {
                data class Follow(var itemType: String?,
                                  var itemId: Int,
                                  var followed: Boolean)

                data class Shield(var itemType: String?,
                                  var itemId: Int,
                                  var shielded: Boolean)
            }

            data class Cover(var feed: String?,
                             var detail: String?,
                             var blurred: String?,
                             var sharing: Any?,
                             var homepage: String?)

            data class WebUrl(var raw: String?,
                              var forWeibo: String?)

            data class Consumption(var collectionCount: Int,
                                   var shareCount: Int,
                                   var replyCount: Int)

            data class PlayInfo(var height: Int,
                                var width: Int,
                                var name: String?,
                                var `type`: String?,
                                var url: String?,
                                var urlList: List<UrlList>?) {
                data class UrlList(var name: String?,
                                   var url: String?,
                                   var size: Int)
            }

            data class Tags(var id: Int,
                            var name: String?,
                            var actionUrl: String?,
                            var adTrack: Any?)
        }
    }
}