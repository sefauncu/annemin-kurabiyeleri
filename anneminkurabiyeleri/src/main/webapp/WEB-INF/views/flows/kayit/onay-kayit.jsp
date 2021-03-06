<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<%@include file="../shared/flows-header.jsp"%>
<div class="container">

	<div class="row">


		<div class="col-sm-6">

			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Kisi Detayi</h4>
				</div>
				<div class="panel-body">

					<div class="text-center">

						<h4>${kayitModel.kullanici.ad}${kayitModel.kullanici.soyad}</h4>

						<h5>Email : ${kayitModel.kullanici.email}</h5>
						<h5>Telefon : ${kayitModel.kullanici.telefon}</h5>
						<h5>Rol : ${kayitModel.kullanici.role}</h5>
					</div>
				</div>
				<div class="panel-footer">
					<a href="${flowExecutionUrl}&_eventId_kisi" class="btn btn-primary">Kisi</a>

				</div>
			</div>

		</div>


		<div class="col-sm-6">

			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Fatura Adresi</h4>
				</div>
				<div class="panel-body">



					<div class="text-center">

						<h4>${kayitModel.fatura.adresBir}</h4>
						<h4>${kayitModel.fatura.adresIki}</h4>
						<h4>${kayitModel.fatura.sehir} - ${kayitModel.fatura.postaKodu}</h4>
						<h4>${kayitModel.fatura.ilce}</h4>
						<h4>${kayitModel.fatura.ulke}</h4>
					 
					</div>

				</div>
				<div class="panel-footer">
					<a href="${flowExecutionUrl}&_eventId_fatura"
						class="btn btn-primary">Adres</a>

				</div>
			</div>

		</div>

	</div>

	<!-- to provide the confirm button after displaying the details -->
	<div class="row">
		<div class="col-sm-4 col-sm-offset-4">

			<div class="text-center">

				<a href="${flowExecutionUrl}&_eventId_gonder"
					class="btn btn-primary">Onay</a>

			</div>

		</div>
	</div>
</div>



<%@include file="../shared/flows-footer.jsp"%>