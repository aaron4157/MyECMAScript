# MyECMAScript
## API implementation with javascript
API(application programming interface) 是一種資料分享服務，第三方開發者藉由特定規格取用資料、並且用於他們開發的程式當中。API概念接近於日常熟悉的鑲嵌網頁元素如Google Map、YouTube、Instagram等，然而提供的內容是純資料、處理邏輯也更多元。
本專案使用原生JS與jQuery技術介接雙北YouBike API，並做JSON資料處理呈現。可以篩選行政區、或是距離最近的站點資訊。
## Cross origin resourse sharing(CORS) Policy
執行NTPquery.html可以發現，前端取得資料的嘗試無效。這是因為新北市YouBike的服務header有嚴格的跨站資源共享(CORS)設定，阻擋不同網域的前端regests。
當瀏覽器處理javascript中發起的request，除了**簡單請求**的情形，都會進行一次預檢(preflight)、比對response head的**Access-Control-Allow-Origin**欄位。若該欄位聲明的網域與origin不同，瀏覽器就取消使用者(嚴格說是腳本)發起的request，並記錄錯誤。
跨域連結的檢核機制，防止跨站腳本(XSS)攻擊、確保資料安全，主流瀏覽器都有採用。為維護資訊安全，建議改在AP端進行資料介接，請參照servlet名稱APIContact.java發起異步請求的做法。
以下是無論如何想要在前端接資料的辦法
 - 滿足簡單請求(simple request)的要件
 - 聯絡服務提供者開放存取權限、response head加上 Access-Control-Allow-Origin:*
## 改版
示範jQuery的篩選套件用法
使用jQuery library渲染資料表格。來源
> [渲染表格的作法](https://www.jqueryscript.net/table/Easy-Table-List-Pagination-Plugin-With-jQuery-Paginathing.html)
> [渲染陣列的作法](https://www.jqueryscript.net/demo/Paginate-Sort-Filter-Table-Sortable/)

