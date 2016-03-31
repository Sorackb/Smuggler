package org.lucassouza.smuggler.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import static org.lucassouza.smuggler.type.Color.BLACK;
import static org.lucassouza.smuggler.type.Color.RED;

/**
 *
 * @author Lucas Souza [lucas.souza@virtual.inf.br]
 */
public class History {

  private final Roulette roulette;
  private final HashMap<String, String> cookies;

  public History() {
    //this.roulette = new Roulette(508000, false, BLACK, RED);
    this.roulette = new Roulette(3000000, 19.00, false, BLACK, RED);
    this.cookies = new HashMap<>();

    this.cookies.put("settings_confirm", "false");
    this.cookies.put("settings_sounds", "false");
    this.cookies.put("settings_dongers", "false");
    this.cookies.put("settings_hideme", "false");
    this.cookies.put("PHPSESSID", "a6eutb3stk9jh5k8fi5pnhpbm4");
    this.cookies.put("visid_incap_655666", "XAYUy4uLSL+5W21jx9LneBkj+VYAAAAAQkIPAAAAAACA1CdzAbsn9R80EjKrc2wsMbpmExBUq8AB");
    this.cookies.put("incap_ses_222_655666", "ZN69XznrZUjBbVmd87QUA8hV/VYAAAAAsIXUCRe9V8jYVtA0eeMSrw==");
  }

  public LinkedHashSet<String> list() throws IOException {
    LinkedHashSet<String> dateList = new LinkedHashSet<>();
    Document webpage;
    Elements cells;

    webpage = Jsoup.connect("http://www.csgodouble.com/rolls.php")
            .userAgent("Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36")
            .cookies(this.cookies)
            .get();
    cells = webpage.select("table > tbody > tr > td:nth-child(1)");

    for (Integer index = cells.size() - 1; index >= 0; index--) {
      Element cell;

      cell = cells.get(index);
      dateList.add(cell.text());
    }

    return dateList;
  }

  public void run(String... dates) throws Exception {
    LinkedHashSet<String> dateList;

    if (dates.length == 0) {
      dateList = this.list();
    } else {
      dateList = new LinkedHashSet<>();

      for (Integer index = dates.length - 1; index >= 0; index--) {
        String date;

        date = dates[index];
        dateList.add(date);
      }
    }

    for (String date : dateList) {
      this.runDate(date);
    }
  }

  public void runDate(String date) throws Exception {
    Document webpage;
    Elements cells;

    webpage = Jsoup.connect("http://www.csgodouble.com/rolls.php?date=" + date)
            .userAgent("Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36")
            .cookies(this.cookies)
            .get();

    cells = webpage.select("table > tbody > tr > td.td-val");

    for (Integer index = cells.size() - 1; index >= 0; index--) {
      Element cell;

      cell = cells.get(index);
      try {
        this.roulette.roll(Integer.parseInt(cell.text()));
      } catch (Exception ex) {
        throw new Exception("Erro no dia " + date + " rodada " + index);
      }
    }
  }
}
