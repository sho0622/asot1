$(document).ready(function () {
    const REQUIRE = '選択してください';

    $("form").submit(function () {
        let form_g = $('.form-group1');
        if ($('input[name="answer1"]:checked').val() === undefined) {
            form_g.removeClass('has-success').addClass('has-error');
            form_g.find('.help-block').text(REQUIRE);
            return false;
        } else {
            form_g.removeClass('has-error').addClass('has-success');
            form_g.find('.help-block').text('');
        }
    });

    $("form").submit(function () {
        let form_g = $('.form-group2');
        if ($('input[name="answer2"]:checked').val() === undefined) {
            form_g.removeClass('has-success').addClass('has-error');
            form_g.find('.help-block').text(REQUIRE);
            return false;
        } else {
            form_g.removeClass('has-error').addClass('has-success');
            form_g.find('.help-block').text('');
        }
    });

    $("form").submit(function () {
        let form_g = $('.form-group3');
        if ($('input[name="answer3"]:checked').val() === undefined) {
            form_g.removeClass('has-success').addClass('has-error');
            form_g.find('.help-block').text(REQUIRE);
            return false;
        } else {
            form_g.removeClass('has-error').addClass('has-success');
            form_g.find('.help-block').text('');
        }
    });

    $("form").submit(function () {
        let form_g = $('.form-group4');
        if ($('input[name="answer4"]:checked').val() === undefined) {
            form_g.removeClass('has-success').addClass('has-error');
            form_g.find('.help-block').text(REQUIRE);
            return false;
        } else {
            form_g.removeClass('has-error').addClass('has-success');
            form_g.find('.help-block').text('');
        }
    });

    $("form").submit(function () {
        let form_g = $('.form-group5');
        if ($('input[name="answer5"]:checked').val() === undefined) {
            form_g.removeClass('has-success').addClass('has-error');
            form_g.find('.help-block').text(REQUIRE);
            return false;
        } else {
            form_g.removeClass('has-error').addClass('has-success');
            form_g.find('.help-block').text('');
        }
    });

    $("form").submit(function () {
        let form_g = $('.form-group6');
        if ($('input[name="answer6"]:checked').val() === undefined) {
            form_g.removeClass('has-success').addClass('has-error');
            form_g.find('.help-block').text(REQUIRE);
            return false;
        } else {
            form_g.removeClass('has-error').addClass('has-success');
            form_g.find('.help-block').text('');
        }
    });

    $("form").submit(function () {
        let form_g = $('.form-group7');
        if ($('input[name="answer7"]:checked').val() === undefined) {
            form_g.removeClass('has-success').addClass('has-error');
            form_g.find('.help-block').text(REQUIRE);
            return false;
        } else {
            form_g.removeClass('has-error').addClass('has-success');
            form_g.find('.help-block').text('');
        }
    });

    $("form").submit(function () {
        let form_g = $('.form-group8');
        if ($('input[name="answer8"]:checked').val() === undefined) {
            form_g.removeClass('has-success').addClass('has-error');
            form_g.find('.help-block').text(REQUIRE);
            return false;
        } else {
            form_g.removeClass('has-error').addClass('has-success');
            form_g.find('.help-block').text('');
        }
    });

    $("form").submit(function () {
        let form_g = $('.form-group9');
        if ($('input[name="answer9"]:checked').val() === undefined) {
            form_g.removeClass('has-success').addClass('has-error');
            form_g.find('.help-block').text(REQUIRE);
            return false;
        } else {
            form_g.removeClass('has-error').addClass('has-success');
            form_g.find('.help-block').text('');
        }
    });

    $("form").submit(function () {
        let form_g = $('.form-group10');
        if ($('input[name="answer10"]:checked').val() === undefined) {
            form_g.removeClass('has-success').addClass('has-error');
            form_g.find('.help-block').text(REQUIRE);
            return false;
        } else {
            form_g.removeClass('has-error').addClass('has-success');
            form_g.find('.help-block').text('');
        }
    });


});


