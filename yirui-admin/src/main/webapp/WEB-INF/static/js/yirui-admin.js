$(function() {

    $('#side-menu').metisMenu();

});

//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
$(function() {
    $(window).bind("load resize", function() {
        topOffset = 50;
        width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.navbar-collapse').addClass('collapse')
            topOffset = 100; // 2-row-menu
        } else {
            $('div.navbar-collapse').removeClass('collapse')
        }

        height = (this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height;
        height = height - topOffset;
        if (height < 1) height = 1;
        if (height > topOffset) {
            $("#page-wrapper").css("min-height", (height) + "px");
        }
    })
});

// 登录验证
$(document).ready(function() {
    $('#loginForm')
        .bootstrapValidator({
            message: '不是一个有效值',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                username: {
                    message: '用户名无效',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        },
                        stringLength: {
                            min: 4,
                            max: 20,
                            message: '用户名长度范围4-20'
                        },
                        /*remote: {
                         url: 'remote.php',
                         message: 'The username is not available'
                         },*/
                        regexp: {
                            //regexp: /^[a-zA-Z0-9_\.]+$/,
                            regexp: /^[\u4E00-\u9FA5\uf900-\ufa2d_a-zA-Z][\u4E00-\u9FA5\uf900-\ufa2d\w]{1,19}$/,
                            message: '用户名支持中英文数字组合'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        stringLength: {
                            min: 5,
                            max: 50,
                            message: '密码长度范围5-50'
                        }
                    }
                }
            }
        });
    /*.on('success.form.bv', function(e) {
     // Prevent form submission
     e.preventDefault();

     // Get the form instance
     var $form = $(e.target);

     // Get the BootstrapValidator instance
     var bv = $form.data('bootstrapValidator');

     // Use Ajax to submit form data
     $.post($form.attr('action'), $form.serialize(), function(result) {
     console.log(result);
     }, 'json');
     });*/
});
