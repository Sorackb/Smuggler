package org.lucassouza.smuggler.model;

/**
 *
 * @author Lucas Souza [sorackb@gmail.com]
 */
public class Bet {

  private Integer step;
  private Integer bind;
  private Integer total;

  public Bet() {
    this.step = 1;
    this.bind = 0;
    this.total = 0;
  }

  public Integer getBind() {
    return bind;
  }

  public void setBind(Integer bind) {
    // Atualiza o total. Caso a aposta tenha sido aumentada incrementa o total. Caso a aposta seja diminuída significa que a aposta foi zerada
    if (bind > this.bind) {
      this.total = this.total + bind;
      this.step = this.step + 1;
    } else {
      this.total = bind;
      this.step = 1;
    }
    
    this.bind = bind;
  }

  public Integer getTotal() {
    return total;
  }

  public Integer getStep() {
    return step;
  }

  public void setStep(Integer step) {
    this.step = step;
  }
}
