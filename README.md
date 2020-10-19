# MyECMAScript
# API implementation with pure javascript
使用純粹的JS與jQuery技術介接雙北YouBike API，並做JSON資料處理呈現。可以篩選行政區、或是距離最近的站點資訊。
# Cross origin resourse sharing(CORS) Issue
執行NTPquery.html可以發現，前端取得資料的嘗試無效。這是因為新北市YouBike的服務header有嚴格的Access-Control-Allow-Origin設定，所以請求遭到瀏覽器阻擋。
跨域連結的限制確保資料安全，各種瀏覽器都有採用。繞過CORS的標準做法有以下
 - 聯絡服務提供者開放Access-Control-Allow-Origin:*
 - 使用jsonp明確安插callback腳本
 - 在後端進行請求
這裡使用最後一種方法(也是唯一有效的方法)，安排servlet(是一種controller，或是再轉給view-model)分批載入動態資料、整理給前端。
