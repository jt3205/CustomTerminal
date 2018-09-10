<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
    	<meta charset="utf-8" />
	    <meta http-equiv="X-UA-Compatible" content="chrome=1" />
	    <title>Selfmade Terminal</title>
	    <link href="https://fonts.googleapis.com/css?family=Inconsolata" rel="stylesheet" type="text/css" />
	    <link rel="stylesheet" type="text/css" href="/views/css.css">
		<!-- <script src="/views/jquery-3.3.1.min.js"></script>
		<link rel="stylesheet" href="/views/bootstrap.min.css">
		<script src="/views/bootstrap.min.js"></script> -->
 	</head>
  	<body>
	    <div id="container">
	      <output></output>
	      <div id="input-line" class="input-line">
	        <div class="prompt"></div><div><input class="cmdline" autofocus /></div>
	      </div>
	    </div>
	</body>
    <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <!-- <script src="/views/js.js"></script> -->
    <script>
		$( document ).ready(function() {
			var setting;
			var uid = window.location.href.substring(31);
			console.log(uid);
			$.ajax({   
				type: "GET",
				url: "/api/setting/get/"+uid,
				contentType:'application/json',
				success:function(data){
					if(data != null) {
						console.log(data);
						setting = data;
						$("#inputInfo").val(data.infoURL);
						$("#inputTextColor").val(data.textColor);
						$("#inputBackgroundColor").val(data.backgroundColor);
						$("body").css({"background-color":data.backgroundColor});
						$(".cmdline").css({"color":data.textColor});
						settingSeq = data.seq;
						init();
					} else {
						alert("초기 데이터를 세팅하세요!");
					}
				},
				error:function(data){
					alert("초기 데이터를 세팅하세요!");
				}
			});

			function init() {
				$('.prompt').html(setting.prompt);
				  $('.prompt').css("color", setting.promptColor);

				  var term = new Terminal('#input-line .cmdline', '#container output');
				  term.init();
				  
				  setInterval(function() {
				    function r(cls, deg) {
				      $('.' + cls).attr('transform', 'rotate('+ deg +' 50 50)')
				    }
				    var d = new Date()
				    r("sec", 6*d.getSeconds())  
				    r("min", 6*d.getMinutes())
				    r("hour", 30*(d.getHours()%12) + d.getMinutes()/2)
				  }, 1000);

			}

			var util = util || {};
			util.toArray = function(list) {
			  return Array.prototype.slice.call(list || [], 0);
			};

			var Terminal = Terminal || function(cmdLineContainer, outputContainer) {
			  window.URL = window.URL || window.webkitURL;
			  window.requestFileSystem = window.requestFileSystem || window.webkitRequestFileSystem;

			  var cmdLine_ = document.querySelector(cmdLineContainer);
			  var output_ = document.querySelector(outputContainer);

			  const CMDS_ = [
			    'cat', 'clear', 'clock', 'date', 'echo', 'help', 'uname', 'whoami'
			  ];
			  
			  var fs_ = null;
			  var cwd_ = null;
			  var history_ = [];
			  var histpos_ = 0;
			  var histtemp_ = 0;

			  window.addEventListener('click', function(e) {
			    cmdLine_.focus();
			  }, false);

			  cmdLine_.addEventListener('click', inputTextClick_, false);
			  cmdLine_.addEventListener('keydown', historyHandler_, false);
			  cmdLine_.addEventListener('keydown', processNewCommand_, false);

			  //
			  function inputTextClick_(e) {
			    this.value = this.value;
			  }

			  //
			  function historyHandler_(e) {
			    if (history_.length) {
			      if (e.keyCode == 38 || e.keyCode == 40) {
			        if (history_[histpos_]) {
			          history_[histpos_] = this.value;
			        } else {
			          histtemp_ = this.value;
			        }
			      }

			      if (e.keyCode == 38) {
			        histpos_--;
			        if (histpos_ < 0) {
			          histpos_ = 0;
			        }
			      } else if (e.keyCode == 40) {
			        histpos_++;
			        if (histpos_ > history_.length) {
			          histpos_ = history_.length;
			        }
			      }

			      if (e.keyCode == 38 || e.keyCode == 40) {
			        this.value = history_[histpos_] ? history_[histpos_] : histtemp_;
			        this.value = this.value;
			      }
			    }
			  }

			  //
			  function processNewCommand_(e) {

			    if (e.keyCode == 9) {
			      e.preventDefault();
			    } else if (e.keyCode == 13) { 
			      if (this.value) {
			        history_[history_.length] = this.value;
			        histpos_ = history_.length;
			      }

			      // Duplicate current input and append to output section.
			      var line = this.parentNode.parentNode.cloneNode(true);
			      line.removeAttribute('id')
			      line.classList.add('line');
			      var input = line.querySelector('input.cmdline');
			      input.autofocus = false;
			      input.readOnly = true;
			      output_.appendChild(line);

			      if (this.value && this.value.trim()) {
			        var args = this.value.split(' ').filter(function(val, i) {
			          return val;
			        });
			        var cmd = args[0].toLowerCase();
			        args = args.splice(1);
			      }

			      switch (cmd) {
			        default:
			          if (cmd) {
			          	$.ajax({   
							type: "GET",
							url: "/api/command/"+cmd+"/"+uid,
							contentType:'application/json',
							success:function(data){
								if(data.text != undefined) {
			            			output(cmd + ': '+data.text);
								} else {
			            			output(cmd + ': command not found');
								}
							},
							error:function(data){
								alert("초기 데이터를 세팅하세요");
							}
						});
			          }
			      };

			      window.scrollTo(0, getDocHeight_());
			      this.value = '';
			    }
			  }

			  function formatColumns_(entries) {
			    var maxName = entries[0].name;
			    util.toArray(entries).forEach(function(entry, i) {
			      if (entry.name.length > maxName.length) {
			        maxName = entry.name;
			      }
			    });

			    var height = entries.length <= 3 ?
			        'height: ' + (entries.length * 15) + 'px;' : '';

			    var colWidth = maxName.length * 7;

			    return ['<div class="ls-files" style="-webkit-column-width:',
			            colWidth, 'px;', height, '">'];
			  }

			  function output(html) {
			    output_.insertAdjacentHTML('beforeEnd', '<div style="padding:10px 0px; color:'+setting.textColor+'">' + html + '</div>');
			  }

			  function getDocHeight_() {
			    var d = document;
			    return Math.max(
			        Math.max(d.body.scrollHeight, d.documentElement.scrollHeight),
			        Math.max(d.body.offsetHeight, d.documentElement.offsetHeight),
			        Math.max(d.body.clientHeight, d.documentElement.clientHeight)
			    );
			  }
			  return {
			    init: function() {
			      output('<img align="left" src="'+setting.imageURL+'" width="100" height="100" style="padding: 0px 10px 20px 0px"><h2 style="letter-spacing: 4px; color:'+setting.titleColor+'">'+setting.title+'</h2><p>' + new Date() + '</p><p>Enter "help" for more information.</p>');
			    },
			    output: output
			  }
			};
		});
    </script>
</html>