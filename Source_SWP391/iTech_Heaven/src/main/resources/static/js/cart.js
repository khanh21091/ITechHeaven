function updateTotalPriceCart() {
    debugger;
    var totalCartPrice = 0; // Khởi tạo tổng tiền

    // Lặp qua tất cả các sản phẩm trong giỏ hàng
    var quantityInputs = document.querySelectorAll('.cart-single-list .row .quantity-input');
    quantityInputs.forEach(function (input) {
        var row = input.closest('.row'); // Lấy phần tử cha có class là '.row'
        var quantity = Math.max(parseFloat(input.value), 1); // Lấy số lượng sản phẩm, đảm bảo không nhỏ hơn 1

        var pricePerItem = 0;
        var discountPercentage = 0;

        if (row.querySelector('.product-price')) {
            pricePerItem = parseFloat(row.querySelector('.product-price').textContent.replace(/[^\d.-]/g, '')); // Lấy giá sản phẩm
        }

        if (row.querySelector('.product-discount')) {
            discountPercentage = parseFloat(row.querySelector('.product-discount').textContent.replace('%', '')) || 0; // Lấy phần trăm giảm giá nếu có
        }

        // Tính giá sau khi đã áp dụng giảm giá
        var priceAfterDiscount = pricePerItem * (1 - discountPercentage / 100);

        // Cộng vào tổng tiền (giá đã giảm giá * số lượng)
        totalCartPrice += priceAfterDiscount * quantity;
    });

    // Hiển thị tổng tiền
    document.getElementById("cartSubtotal").textContent = totalCartPrice.toFixed(2) + 'đ';
}

// Gọi hàm updateTotalPrice khi trang được tải hoàn thành và mỗi khi số lượng sản phẩm thay đổi
document.addEventListener('DOMContentLoaded', function() {
    updateTotalPriceCart();

    var quantityInputs = document.querySelectorAll('.cart-single-list .row .quantity-input');
    quantityInputs.forEach(function(input) {
        input.addEventListener('input', function() {
            if (parseFloat(this.value) < 1) {
                this.value = 1; // Đảm bảo số lượng không nhỏ hơn 1
            }
            updateTotalPriceCart();
        });
    });
});
