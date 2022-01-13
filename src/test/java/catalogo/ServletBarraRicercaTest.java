/*package catalogo;

import com.google.gson.Gson;
import controller.catalogo.ServletBarraRicerca;
import model.prodotto.ProdottoDAO;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ServletBarraRicercaTest {

    @Mock
    private ProdottoDAO prodottoDAO;
    @Mock
    private Gson gson;

    private ServletBarraRicerca servletBarraRicerca;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        servletBarraRicerca = new ServletBarraRicerca(prodottoDAO);
    }

    @Test // Da finire
    public void doGetTest() throws ServletException, IOException {
        HttpServletRequest request=mock(HttpServletRequest.class);
        HttpServletResponse response=mock(HttpServletResponse.class);
        when(request.getParameter("value")).thenReturn("TACHIPIRINA");
        String stringa = request.getParameter("value");

        ArrayList<Prodotto> prodotti = new ArrayList<>();
        prodotti.add(new Prodotto());
        prodotti.add(new Prodotto());
        prodotti.add(new Prodotto());
        prodotti.add(new Prodotto());

        when(prodottoDAO.prodottoSearch(stringa)).thenReturn(prodotti);


        Gson gson = mock(Gson.class);
        when(gson.toJson(prodotti)).thenReturn("PROVA");
        servletBarraRicerca.doGet(request, response);
        verify(response).setContentType("application/json");



    }

    @Test // Da finire
    public void ricercaProdottoTest(){

    }

}
*/