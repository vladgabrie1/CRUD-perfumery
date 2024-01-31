$(document).ready(function() {
    $("#priceRange").ionRangeSlider({
        type: "double",
        min: 0,
        max: 1000,
        from: 0,
        to: 1000,
        step: 1,
        postfix: "$",
        grid: false,
        hide_min_max: true, // Hide minimum and maximum labels
        hide_from_to: false, // Hide "From" and "To" labels
        prettify_enabled: true, // Disable prettifying numbers
        force_edges: true,
        skin: "round"
    });
});