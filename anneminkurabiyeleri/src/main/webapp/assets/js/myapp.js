$(function() {
	switch (menu) {

	case 'Hakkimizda':
		$('#hakkimizda').addClass('active');
		break;
	case 'Iletisim':
		$('#iletisim').addClass('active');
		break;

	case 'Tum Urunler':
		$('#urunleriListele').addClass('active');
		break;
	default:
		if (menu == "Anasayfa")
			break;
		$('#urunleriListele').addClass('active');

		break;
	}

	switch (numara) {
	case numara:
		$('#' + numara).addClass('active');
		break;

	default:
		break;
	}

	// datatable jquery için kodlar

	var $table = $('#urunleriListeleTable');

	// bu ttabloya ait ürünleri getir

	if ($table.length) {
		// console.log('tablodakiler');

		var jsonUrl = '';
		if (window.kategoriId == '') {

			jsonUrl = window.contextRoot + '/json/data/hepsi/urunler';
		} else {

			jsonUrl = window.contextRoot + '/json/data/kategori/'
					+ window.kategoriId + '/urunler';
		}

		$table
				.DataTable({

					lengthMenu : [ [ 3, 5, 10, -1 ],
							[ '3 Kayit', '5 Kayit', '10 Kayit', 'Hepsi' ] ],
					pageLenght : 5,
					ajax : {

						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'kod',
								bSortable : false,
								mRender : function(data, type, row) {

									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class="dataTableImg"/>';
								}

							},
							{
								data : 'ad'

							},
							{
								data : 'marka'

							},
							{
								data : 'fiyat',
								mRender : function(data, type, row) {

									return data + ' &#8378;'
								}

							},
							{
								data : 'miktar'

							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {

									var str = '';
									str += '<a href="'
											+ window.contextRoot
											+ '/goster/'
											+ data
											+ '/urun" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a>&#160';
									str += '<a href="'
											+ window.contextRoot
											+ '/sepet/ekle/'
											+ data
											+ '/urun" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
									return str;
								}

							} ]
				});
	}

});