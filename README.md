# Furniture Shopping App

Furniture Shopping App là một ứng dụng mua sắm đồ nội thất được xây dựng bằng Kotlin và Java, sử dụng Gradle để quản lý dependencies.
Ứng dụng được thiết kế dựa trên: [Figma](https://www.figma.com/design/dTdGEtZoQd2uRZc8qS5xjr/Timberr?node-id=0-1)
## Chức năng

Ứng dụng cung cấp các chức năng sau:

- Bảo mật đăng ký đăng nhập
- Duyệt và tìm kiếm sản phẩm đồ nội thất.
- Thêm sản phẩm vào giỏ hàng.
- Đặt hàng
- Xem chi tiết sản phẩm.
- Xem lại thông báo, giỏ hàng, đặt hàng,
- Quản lý hồ sơ cá nhân

## Công nghệ sử dụng

- Ngôn ngữ lập trình: Kotlin và Java.
- Quản lý dependencies: Gradle.
- Giao diện người dùng: Android Jetpack Compose.
- Database: Supabase.

## Database

Ứng dụng sử dụng Supabase làm hệ thống database. Supabase cung cấp API RESTful cho việc truy vấn dữ liệu và cũng hỗ trợ real-time updates.

## Giao diện

Ứng dụng sử dụng Android Jetpack Compose để xây dựng giao diện người dùng. Jetpack Compose là một thư viện UI hiện đại, giúp xây dựng giao diện người dùng một cách đơn giản và hiệu quả.
<details>
<summary><b>Authentication</b></summary>
<img alt="Register" loading="lazy" src="preview/product-detail.png" height="587px" width="256px"/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img alt="Login" loading="lazy" src="preview/product-detail.png" height="587px" width="256px"/>
</details><br>
## Cài đặt

Đầu tiên, clone repository này về máy của bạn:

```bash
git clone https://github.com/nqmgaming/FurnitureShoppingApp.git
