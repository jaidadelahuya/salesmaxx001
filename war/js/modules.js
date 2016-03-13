function required($input) {
	var val = $input.val();
	if (!(val === null)) {

		if (val.length == 0) {
			return false;
		} else {
			return true;
		}
	} else {
		return false;
	}
}

function allNumeric($input) {
	var val = $input.val();
	if (!(val === null)) {
		var numbers = /^[0-9]+$/;
		if (val.match(numbers)) {
			return true;
		} else {
			return false;
		}
	} else {
		return false;
	}

}

function clearForm(form) {
	form[0].reset();
}

function validateEmail($input) {
	var val = $input.val();
	if (!(val === null)) {
		if (/^\S+@\S+\.\S+/.test(val)) {
			return (true)
		} else {
			return false;
		}
	} else {
		return false;
	}
	
}

function addPopOver() {

	$('#imginfo').webuiPopover({
		placement : 'auto',
		width : 'auto',
		height : 'auto',
		trigger : 'click',
		arrow : true,
		content : $('#popover-content').html(),
		closeable : false,
		padding : true,
		type : 'html'
	});
}

function addError(msgD, msg) {
	msgD.removeClass("alert-success");
	msgD.addClass("alert alert-danger");
	msgD.html(msg);
}

function addSuccess(msgD, msg) {
	msgD.removeClass("alert-danger");
	msgD.addClass("alert alert-success");
	msgD.html(msg);
}

function getAddToCartData(href) {
	var b = href.substring(href.indexOf("=")+1,href.indexOf("&"));
	var c = href.substring(href.lastIndexOf("=")+1);
	var d = {
			id : b,
			qty : c
	}
	return d;
}

function validateContactForm() {
	
	var msgDiv = $("#contact-form-msg-div");
	var ok =  required($("#name"));
	if(!ok) {
		addError(msgDiv,"Enter your name");
		return false;
	}
	
	ok =  required($("#contact-email"));
	if(!ok) {
		addError(msgDiv,"Enter your email address");
		return false;
	}
	
	ok = validateEmail($("#contact-email"));
	if(!ok) {
		addError(msgDiv,"Enter a valid email address");
		return false;
	}
	
	ok = required($("#contact-msg"));
	if(!ok) {
		addError(msgDiv,"Enter your message");
		return false;
	}
	
	return true;
}

function loginButtonFunction() {
	$("#login-modal").modal({
		backdrop: "static",
		keyboard: false
	});
	$("#login-button").on('click',function() {
		$("#login-form-div").waitMe({
			effect : 'ios',
			color : '#b1010c',
			sizeW : '15',
			sizeH : '15'
		});
		
		var jqxhr = $.post("/sm/open/login", $("#login-form").serialize()).done(function(data) {
			window.location.assign(data);
			}).error(function(jqXHR,status,errorThrown) {
				var status = jqXHR.status;
				if(status === 800) {
					addError($("#msg-div-1"),"You have to enter your email or Registration ID.");
				} else if(status === 801) {
					addError($("#msg-div-1"),"You have to enter your password.");
				}  else if(status === 802) {
					addError($("#msg-div-1"),"The username/password you entered do not match.");
				}  else if(status === 803) {
					addError($("#msg-div-1"),"The Registration ID "+$("#lg-username").val()+" does not exist.");
				}  
				
				$("#login-form-div").waitMe("hide");
			});
	});
}

function forgotPasswordAction() {
	$("#login-form-div").load("/partials/forgot-password.html", function() {
		$("#forgot-password-button").on('click', function() {
			$("#login-form-div").waitMe({
				effect : 'ios',
				color : '#b1010c',
				sizeW : '15',
				sizeH : '15'
			});
			var jqxhr = $.post("/sm/open/forgot-password", $("#forgot-password-form").serialize()).done(function(data) {
				$("#login-form-div").load("/partials/confirmation-code.html", function() {
					$("#email-output").text(data);
					$("#confirm-button").off();
					$("#confirm-button").on("click", function() {
						$("#login-form-div").waitMe({
							effect : 'ios',
							color : '#b1010c',
							sizeW : '15',
							sizeH : '15'
						});
						var jqxhr = $.post("/sm/open/confirm-code", $("#confirm-code-form").serialize()).done(function(data) {
							$("#login-form-div").load("/partials/change-password.html", function() {
								$("#change-password-button").on('click', function() {
									$("#login-form-div").waitMe({
										effect : 'ios',
										color : '#b1010c',
										sizeW : '15',
										sizeH : '15'
									});
									var jqxhr = $.post("/sm/open/change-password-from-login", $("#change-password-form").serialize()).done(function() {
										$("#change-password-msg-div").removeClass("alert-info");
										addSuccess($("#change-password-msg-div"), "<p>Your password has been changed successfully</p>");
										$("#change-password-button").attr('disabled','disabled');
										$("#pass1").attr('disabled','disabled');
										$("#pass2").attr('disabled','disabled');
								
									}).error(function(xhr) {
										var status = xhr.status;
										if(status === 800) {
											addError($("#change-password-msg-div"),"You have to retype your password.");
										} else if(status === 802) {
											addError($("#change-password-msg-div"),"You have to enter a password.");
										} else if(status === 801) {
											addError($("#change-password-msg-div"),"The passwords do not match.");
										}
									}).always(function() {
										$("#login-form-div").waitMe("hide");
									});
								});
							});
							$("#login-footer").html("<p></p>");
						}).error(function(xhr) {
							var status = jqxhr.status;
							if(status === 800) {
								addError($("#confirm-form-msg-div"),"Bad request! please try again later.");
							} else if(status === 801) {
								addError($("#confirm-form-msg-div"),"The confirmation code "+$("#confirmation-code").val()+" is not valid for this entry");
							} else if(status === 802) {
								addError($("#confirm-form-msg-div"),"You have to enter your confirmation code");
							}
						}).always(function() {
							$("#login-form-div").waitMe('hide');
						});
					});
				});
				
				$("#login-footer").load('/partials/confirm-dialog-footer.html',function() {
					$("#resend-code").on("click", function(e) {
						e.preventDefault();
						$("#login-form-div").waitMe({
							effect : 'ios',
							color : '#b1010c',
							sizeW : '15',
							sizeH : '15'
						});
						$.ajax({
							url : $(this).prop("href"),
							success : function () {
								$("#confirm-form-msg-div").removeClass("alert-info");
								$("#confirm-form-msg-div").empty();
								addSuccess($("#confirm-form-msg-div"),"Confirmation Code re-sent successfully");
							},
							error: function(xhr,status,errorThrown) {
								$("#confirm-form-msg-div").removeClass("alert-info");
								$("#confirm-form-msg-div").empty();
								addError($("#confirm-form-msg-div"),"<p>We could not resend a confirmation code</p><p>Please try again later.</p>");
							},
							complete : function() {
								$("#login-form-div").waitMe("hide");
							}
						});
					});
				});
			}).error(function(xhr) {
				var status = xhr.status;
				if(status === 800) {
					addError($("#forgot-password-msg-div"),"You have to enter your email or Registration ID.");
				} else if(status === 801) {
					addError($("#forgot-password-msg-div"),"The email " + $("#fp-username").val()
								+ " is not associated with any account.");
				}  else if(status === 802) {
					addError($("#forgot-password-msg-div"),"We could not send a confirmation code at this time.<p>Please check if your email is typed correctly and try again.<p/>");
				}  else if(status === 803) {
					addError($("#forgot-password-msg-div"),"The ID " + $("#fp-username").val()
								+ " is not associated with any account.");
				} 
			}).always(function() {
				$("#login-form-div").waitMe('hide');
			});
				
			
		});
	});
}

$(document).ready(function() {
	
	$("#write-a-testimonial").click(function(e) {
		e.preventDefault();
		$("#testimonial-div").slideToggle();
	});
	
	$(".order-details-button").click(function(e) {
		e.preventDefault();
		var parent = $(this).parent().parent().parent().parent().parent();
		parent.find(".order-details-div").slideToggle();
	});
	
	$(".date-picker").datepicker({
	
	});
	
	$("#proceed-to-checkout").show();
	$("#cancel-policy").click(function() {
		  $("#make-payment").attr("disabled", !$(this).is(":checked"));
		  
	});
	
	$("#proceed-to-checkout").click(function() {
		$("#payment-mode-div").slideToggle();
	});
	
	(".make-payment-interswitch").click(function (e) {
		e.preventDefault();
		
		$.ajax({
			url: "/sm/closed/save-interswitch-transaction",
			success: function () {
				$("#interswitch-tansaction-form").submit();
			}, 
			error : function() {
				alert("Cannot make your payment request now. Try again in 30 secs");
			}
		
		});
	});
	
	$(".workshop-information-accordion").click(function() {
		parent = $(this).parent();
		parent.find(".workshop-information-accordion-tab").slideToggle( function() {
			parent.find(".workshop-info-triangle").toggleClass("glyphicon-triangle-bottom glyphicon-triangle-top");
		});
	});
	
	$("#enrolled-Workshops-Header").click(function() {
		$("#enrolled-Workshops-div").slideToggle(function () {
			$("#enrolled-workshop-triangle").toggleClass("glyphicon-triangle-bottom glyphicon-triangle-top");
		});
	});
	
	
	
	/*$(".sign-up-btn").on('click', function() {
		$("#sign-up-form-div").load('/signup-form.html',function() {
			$("#register-modal").modal({
				backdrop: "static",
				keyboard: false
			});
			$(".sign-up-button").on("click", function() {
				$("#sign-up-form-div").waitMe({
					effect : 'ios',
					color : '#b1010c',
					sizeW : '15',
					sizeH : '15'
				});
				var jqxhr = $.post("/sm/open/register-user", $("#sign-up-form").serialize()).done(function(data) {
					$("#sign-up-form-div").load("/partials/confirmation-code.html", function() {
						$("#email-output").text(data);
						$('#confirm-button').off();
						$('#confirm-button').on('click', function() {
							$("#sign-up-form-div").waitMe({
								effect : 'ios',
								color : '#b1010c',
								sizeW : '15',
								sizeH : '15'
							});
							var jqxhr = $.post("/sm/open/confirm-code", $("#confirm-code-form").serialize()).done(function(data) {
								$("#sign-up-form-div").load("/partials/signup-congrats-msg.html",function() {
									$("#congrats-reg-id").text(data);
								});
								$("#sign-up-footer").load('/partials/signup-congrats-footer.html');
							
						}).error(function(jqxhr) {
							var status = jqxhr.status;
							if(status === 800) {
								addError($("#confirm-form-msg-div"),"Bad request! please try again later.");
							} else if(status === 801) {
								addError($("#confirm-form-msg-div"),"The confirmation code "+$("#confirmation-code").val()+" is not valid for this entry");
							} else if(status === 802) {
								addError($("#confirm-form-msg-div"),"You have to enter your confirmation code");
							}
						}).always(function() {
							$("#sign-up-form-div").waitMe("hide");
						});
					});
					
					$("#sign-up-footer").load('/partials/confirm-dialog-footer.html',function() {
						$("#resend-code").on("click", function(e) {
							e.preventDefault();
							$("#sign-up-form-div").waitMe({
								effect : 'ios',
								color : '#b1010c',
								sizeW : '15',
								sizeH : '15'
							});
							$.ajax({
								url : $(this).prop("href"),
								success : function () {
									$("#confirm-form-msg-div").removeClass("alert-info");
									$("#confirm-form-msg-div").empty();
									addSuccess($("#confirm-form-msg-div"),"Confirmation Code re-sent successfully");
								},
								error: function(xhr,status,errorThrown) {
									$("#confirm-form-msg-div").removeClass("alert-info");
									$("#confirm-form-msg-div").empty();
									addError($("#confirm-form-msg-div"),"<p>We could not resend a confirmation code</p><p>Please try again later.</p>");
								},
								complete : function() {
									$("#sign-up-form-div").waitMe("hide");
								}
							});
						});
					});
				}).error(function(jqXHR,status,errorThrown) {
					$("#confirm-form-msg-div").removeClass("alert-info");
					$("#confirm-form-msg-div").empty();
					addError($("#confirm-form-msg-div"),errorThrown);
				});
				
				
				}).error(function(jqXHR, status, errorThrown) {
					status = jqXHR.status;
					if(status === 800) {
						addError($("#msg-div"),"You have to enter your First name.");
					} else if(status === 801) {
						addError($("#msg-div"),"You have to enter your last Name.");
					}  else if(status === 802) {
						addError($("#msg-div"),"You have to enter your email address.");
					}  else if(status === 803) {
						addError($("#msg-div"),"You have to enter a password.");
					}  else if(status === 804) {
						addError($("#msg-div"),"The passwords do not match.");
					}  else if(status === 805) {
						addError($("#msg-div"),"The email " + $("#email").val() + " already exists.");
					}  else if(status === 806) {
						addError($("#msg-div"),"The email address " + $("#email").val()
									+ " is either invalid or does not exist.");
					}  else if(status === 807) {
						addError($("#msg-div"),"We could not send a confirmation code to " + $("#email").val()
									+ ". Please try again later.");
					}  
					
				}).always(function() {
					$("#sign-up-form-div").waitMe("hide");
					
				});
			});
		});
		
	});*/
	
	
	
	$("#edit-profile-save-button").click(function() {
		$("#edit-profile-div").waitMe({
			effect : 'ios',
			color : '#b1010c',
			sizeW : '5',
			sizeH : '5'
		});
		
		$.ajax({
			url : "/sm/closed/edit-profile",
			data : $("#edit-profile-form").serialize(),
			type : "POST",
			success: function(data) {
				addSuccess($("#edit-profile-msg-div"),'Your Profile has been updated.');
			},
			error : function(xhr) {
				addError($("#edit-profile-msg-div"),'Your profile has not been updated.');
			},
			complete : function() {
				$("#edit-profile-div").waitMe('hide');
			}
		});
	});
	
	
	$("#change-email-button").click(function() {
		$("#change-email-div").waitMe({
			effect : 'ios',
			color : '#b1010c',
			sizeW : '15',
			sizeH : '15'
		});
	});
	
	$("#user-change-password-button").click(function() {
		$("#user-change-password-div").waitMe({
			effect : 'ios',
			color : '#b1010c',
			sizeW : '15',
			sizeH : '15'
		});
		
		$.ajax({
			url: $("#user-change-password-form").prop('action'),
			data : $("#user-change-password-form").serialize(),
			type: "POST",
			success: function(data) {
				addSuccess($("#user-change-password-msg-div"),"Your password has been changed.");
			},
			error : function(xhr) {
				var status = xhr.status;
				if(status === 800) {
					addError($("#user-change-password-msg-div"),"You have to enter a password.");
				} else if(status === 801) {
					addError($("#user-change-password-msg-div"),"You have to retype your password.");
				} else if(status === 802) {
					addError($("#user-change-password-msg-div"),"The passwords do not match.");
				} else if(status === 804) {
					addError($("#user-change-password-msg-div"),"The old password your entered is not correct.");
				} else if(status === 805) {
					addError($("#user-change-password-msg-div"),"You have to enter your old password.");
				}
			},
			complete: function() {
				$("#user-change-password-div").waitMe('hide');
				
			}
		});
	});
	
	$('[data-toggle="tooltip"]').tooltip();
	
	$("#add-mailing-list").click(function(e) {
		var ok = required($("#mailing-list-email"));
		if(ok) {
			ok = validateEmail($("#mailing-list-email"));
			if(!ok) {
				addError($("#mailing-list-msg-div"),"Enter a valid email address.");
				return;
			} else {
				$("#mailing-list-div").waitMe({
					effect : 'ios',
					color : '#b1010c',
					sizeW : '10',
					sizeH : '10'
				});
				e.preventDefault();
				$.ajax({
					url: "/sm/open/add-mailing-list",
					type: "POST",
					data:{
						"email" :  $("#mailing-list-email").val()
					},
					success : function(data) {
						addSuccess($("#mailing-list-msg-div"), "Your email has been added to our mailing list.");
					},
					error : function() {
						addError($("#mailing-list-msg-div"), "An error occured. Please try again.");
					},
					complete : function() {
						$("#mailing-list-div").waitMe('hide');
						clearForm($("#contact-form"));
					}
				});
			}
		} else {
			addError($("#mailing-list-msg-div"),"Enter your email address.");
			return;
		}
	});
	
	$("#contact-send").click(function(e) {
		var ok = validateContactForm();
		if(!ok) {
			return;
		} else {
			$("#contact-form-div").waitMe({
				effect : 'ios',
				color : '#b1010c',
				sizeW : '10',
				sizeH : '10'
			});
			e.preventDefault();
			$.ajax({
				url: "/sm/open/send-mail",
				type: "POST",
				data: $("#contact-form").serialize(),
				success : function(data) {
					addSuccess($("#contact-form-msg-div"), "Your message has been sent successfully.");
				},
				error : function() {
					addError($("#contact-form-msg-div"), "Your message was not been sent. Please try again later.");
				},
				complete : function() {
					$("#contact-form-div").waitMe('hide');
					clearForm($("#contact-form"));
				}
			});
		}
		
		
	});
	
	/*$("#post-discussion").click(function(e) {
		e.preventDefault();
		$.ajax({
			url: $("#post-discussion-form").prop("action"),
			success : function(data) {
				if(data === "false") {
					$("#login-form-div").load("/login.html", function() {
						loginButtonFunction();
						$("#forgot-password").on('click',function(event) {
							event.preventDefault();
							forgotPasswordAction();
						});
					});
				} else {
					$("#post-discussion-form").submit();
				}
				
			}
		});
	});*/
	
	
	$("#signup-congrats-login-button").on("click", function(e) {
		e.preventDefault();
		
		location.reload(function() {
			$("#login-modal").modal({
				backdrop: "static",
				keyboard: false
			});
		});
	});
	
	$(".details-button").click(function() {
		var parent = $(this).closest('tr');
		parent.next().slideToggle();
	});
	
	$("#clear-cart-button").click(function(e) {
		e.preventDefault();
		$.ajax({
			url : "/sm/open/clear-cart",
			type : "POST",
			success : function() {
				window.location.assign("/sm/open/add-to-cart");
			},
			error : function() {
				alert("we can not clear your cart at this time");
			}
		});
	});
	
	$(".remove-from-cart").change(function(e) {
	
		var v = $(this).prop('value');
		$.ajax({
			url : "/sm/open/clear-cart",
			type : "POST",
			data: {
				"id":v
			},
			success : function() {
				window.location.assign("/sm/open/add-to-cart");
			},
			error : function() {
				alert("We can not remove this item at this time.");
			}
		});
	});
	
	$(".increment-qty").change(function(e) {
		
		var v = $(this).closest("td").prop("id");
		var qty = $(this).prop('value');
		$.ajax({
			url : "/sm/open/clear-cart",
			type : "POST",
			data: {
				"id":v,
				"qty":qty
			},
			success : function() {
				window.location.assign("/sm/open/add-to-cart");
			},
			error : function() {
			}
		});
	});
	
	$(".add-to-cart").click(function(e) {
		e.preventDefault();
		var x = getAddToCartData($(this).prop("href"));
		var y = "/sm/open/add-to-cart";
		
		$.ajax({
			url : y,
			type: "POST",
			data: x,
			success : function(data) {
				window.location.assign(data);
			}
		})
	});
	
	$(".control-elem").on('change', function() {
		var val = "";
		if(!($(this).is(':checked'))) {
			val = "-"+$(this).prop('value');
			$(this).prop('value',val);
		}
		$("#sidebar-form").submit();
	});
	
	$(".control-elem-date").on('change', function() {
		
		$("#sidebar-form").submit();
	});
	
	
	$(".login-at-footer").click(function(e) {
		e.preventDefault();
		$("#register-modal").modal("hide");
	});
	
	$("#imginfo").mouseenter(function() {
		addPopOver();
	});
	
	$("#year").change(function() {
		$("#month-input").prop("disabled",true);
	});
	$("#both").change(function() {
		$("#month-input").prop("disabled",false);
	});
		
	
});