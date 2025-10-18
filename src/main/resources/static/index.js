/**
 * Js cho trang index.html
 */

const slider = document.getElementById("price-slider");
const min = document.getElementById("price-min");
const max = document.getElementById("price-max");

// Lấy các input ẩn
const minPrice = document.getElementById("minPrice");
const maxPrice = document.getElementById("maxPrice");

// Lấy giá trị khởi tạo cho range
const initialMin = Number(minPrice.value) || 0;
const initialMax = Number(maxPrice.value) || 100000000;

noUiSlider.create(slider, {
    start: [initialMin, initialMax], // <-- SỬ DỤNG GIÁ TRỊ TỪ THYMELEAF
    connect: true,
    range: {
        min: 0,
        max: 100000000,
    },
    step: 100000,
    tooltips: true,
    format: {
        to: function (value) {
            return Math.round(value).toLocaleString("vi-VN") + " ₫";
        },
        from: function (value) {
            return Number(value.replace(/\D/g, ""));
        },
    },
});

slider.noUiSlider.on("change", (values, handle) => {
    if (handle === 0) {
        min.textContent = values[0];
    } else {
        max.textContent = values[1];
    }

    // Cập nhật rangePrice input
    const minPrice = document.getElementById("minPrice");
    const maxPrice = document.getElementById("maxPrice");

    // Lấy giá trị số nguyên, không format, để gửi backend
    // vì hàm format đã đảm bảo values[i] là giá trị đã được xử lý (chuỗi đã format).
    // Dùng hàm `from` của format để lấy giá trị số nguyên:
    const rawMin = slider.noUiSlider.options.format.from(values[0]);
    const rawMax = slider.noUiSlider.options.format.from(values[1]);

    minPrice.value = rawMin;
    maxPrice.value = rawMax;

    // Nếu slider được kéo, reset priceOption (để đảm bảo chỉ có rangePrice được gửi)
    document.getElementById("priceOption").value = "";
});

// Lắng nghe sự kiện 'update' để CẬP NHẬT TEXT khi người dùng kéo
slider.noUiSlider.on("update", (values, handle) => {
    // Cập nhật text content trực tiếp
    min.textContent = values[0];
    max.textContent = values[1];

    // **QUAN TRỌNG:** KHÔNG CẬP NHẬT input ẩn ở đây!
    // ...
});

// Set value cho input sortPrice khi submit button
function setSortAndSubmit(sortValue) {
    // 1. Cập nhật giá trị của trường ẩn
    document.getElementById("sortPrice").value = sortValue;

    // 3. Gửi form
    if (handleSubmitForm()) {
        // Chạy logic disabled
        document.getElementById("filterForm").submit();
    }
}

const setPriceOptionAndSubmit = (optionParam) => {
    console.log(optionParam);

    document.getElementById("priceOption").value = optionParam;

    // 2. Logic loại trừ: Reset Range Slider input (quan trọng!)
    document.getElementById("minPrice").value = "";
    document.getElementById("maxPrice").value = "";

    // Gọi hàm xử lý và submit
    if (handleSubmitForm()) {
        // Chạy logic disabled
        document.getElementById("filterForm").submit();
    }
};

/**
 * Chạy trước khi form submit
 * Vô hiệu hóa các trường rỗng để không hiển thị lên URL
 */

const form = document.getElementById("filterForm");
const search = document.getElementById("search");
const sortPrice = document.getElementById("sortPrice");
const priceOption = document.getElementById("priceOption");
const categorySlug = document.getElementById("categorySlug");

const handleSubmitForm = () => {
    // === Logic loại trừ lẫn nhau và vô hiệu hóa
    if (categorySlug.value === "") {
        categorySlug.disabled = true;
    }

    if (search.value === "") {
        search.disabled = true;
    }

    if (sortPrice.value === "") {
        sortPrice.disabled = true;
    }

    if (priceOption.value === "") {
        priceOption.disabled = true;
    }

    if (minPrice.value === "") {
        minPrice.disabled = true;
    }

    if (maxPrice.value === "") {
        maxPrice.disabled = true;
    }

    // Đặt lại action mặc định
    let newAction = "/products";

    
    const slug = categorySlug.value
    
    if (slug && slug.trim() !== "") {
        console.log(slug);
        
        newAction = `/danh-muc/${slug.trim()}`;

        // Vì Slug đã được đưa vào URL path
        // Xóa para categorySlug khỏi query string
        categorySlug.disabled = true;
    }

    
    document.getElementById("filterForm").action = newAction;
    
    return true;
};

const formatPrice = (price) => {
    return Math.round(price).toLocaleString("vi-VN") + " ₫";
};

// Tách rangePrice
const parseRangePrice = (rangePrice) => {
    parts = rangePrice.split("-");

    return `${formatPrice(parts[0])} - ${formatPrice(parts[1])}`;
};

/**
 * Hàm xử lý submit Form với category
 */
const setCategoryAndSubmit = (slug) => {    
    // Gán giá trị cho categorySlug input để form lấy.
    categorySlug.value = slug;

    // Gọi hàm xử lý và submit
    if (handleSubmitForm()) {
        // Chạy logic disabled
        form.submit();
    }
};

/**
 * Hàm reset categorySlug
 * Click nút cancel ở cái tag category ở mobile
 */
const resetCategoryAndSubmit = () => {
    categorySlug.value = ''; 
    
    // Gọi hàm xử lý và submit
    if (handleSubmitForm()) {
        // Chạy logic disabled
        form.submit();
    }
}
