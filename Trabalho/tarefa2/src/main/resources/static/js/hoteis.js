$(function () {
  const API = '/api/hoteis';

  function esc(t) {
    return $('<div>').text(t == null ? '' : t).html();
  }

  function mensagem(texto, tipo) {
    $('#mensagem').attr('class', tipo).text(texto).show();
    setTimeout(function () { $('#mensagem').hide(); }, 5000);
  }

  function erroAjax(xhr) {
    const msg = (xhr.responseJSON && xhr.responseJSON.erro) || 'Falha na comunicação com o servidor';
    mensagem(msg, 'erro');
  }

  function limparForm() {
    $('#formHotel')[0].reset();
    $('#id').val('');
    $('#tituloForm').text('Novo hotel');
  }

  function carregar() {
    $.getJSON(API).done(function (hoteis) {
      const $tb = $('#tabela').empty();
      if (!hoteis.length) {
        $tb.append('<tr><td colspan="5" class="vazio">Nenhum hotel cadastrado.</td></tr>');
        return;
      }
      hoteis.forEach(function (h) {
        $tb.append(
          '<tr>' +
          '<td>' + h.id + '</td>' +
          '<td>' + esc(h.nome) + '</td>' +
          '<td>' + esc(h.endereco) + '</td>' +
          '<td>' + (h.categoria != null ? '★'.repeat(h.categoria) : '-') + '</td>' +
          '<td class="botoes">' +
            '<a class="botao pequeno" href="/quartos?hotelId=' + h.id + '">Quartos</a> ' +
            '<button class="pequeno btn-editar" data-id="' + h.id + '">Editar</button> ' +
            '<button class="pequeno perigo btn-excluir" data-id="' + h.id + '">Excluir</button>' +
          '</td></tr>'
        );
      });
    }).fail(erroAjax);
  }

  $('#formHotel').on('submit', function (e) {
    e.preventDefault();
    const id = $('#id').val();
    const cat = $('#categoria').val();
    const dados = {
      nome: $('#nome').val().trim(),
      endereco: $('#endereco').val().trim(),
      categoria: cat === '' ? null : parseInt(cat, 10)
    };
    $.ajax({
      url: id ? API + '/' + id : API,
      method: id ? 'PUT' : 'POST',
      contentType: 'application/json',
      data: JSON.stringify(dados)
    }).done(function () {
      mensagem(id ? 'Hotel atualizado com sucesso.' : 'Hotel cadastrado com sucesso.', 'ok');
      limparForm();
      carregar();
    }).fail(erroAjax);
  });

  $('#tabela').on('click', '.btn-editar', function () {
    $.getJSON(API + '/' + $(this).data('id')).done(function (h) {
      $('#id').val(h.id);
      $('#nome').val(h.nome);
      $('#endereco').val(h.endereco);
      $('#categoria').val(h.categoria);
      $('#tituloForm').text('Editar hotel #' + h.id);
      window.scrollTo({ top: 0, behavior: 'smooth' });
    }).fail(erroAjax);
  });

  $('#tabela').on('click', '.btn-excluir', function () {
    const id = $(this).data('id');
    if (!confirm('Excluir o hotel #' + id + '?')) return;
    $.ajax({ url: API + '/' + id, method: 'DELETE' }).done(function () {
      mensagem('Hotel excluído.', 'ok');
      carregar();
    }).fail(erroAjax);
  });

  $('#btnCancelar').on('click', limparForm);

  carregar();
});
