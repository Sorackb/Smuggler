package org.lucassouza.smuggler.type;

/**
 *
 * @author Lucas Souza [sorackb@gmail.com]
 */
public enum Color {
  GREEN("panel0-0", 0, 0),
  RED("panel1-7", 1, 7),
  BLACK("panel8-14", 8, 14);

  private String panelId;
  private Integer start;
  private Integer end;

  private Color(String panelId, Integer start, Integer end) {
    this.panelId = panelId;
    this.start = start;
    this.end = end;
  }

  /**
   * @return the panelId
   */
  public String getPanelId() {
    return panelId;
  }

  /**
   * @param panelId the panelId to set
   */
  public void setPanelId(String panelId) {
    this.panelId = panelId;
  }

  /**
   * @return the start
   */
  public Integer getStart() {
    return start;
  }

  /**
   * @param start the start to set
   */
  public void setStart(Integer start) {
    this.start = start;
  }

  /**
   * @return the end
   */
  public Integer getEnd() {
    return end;
  }

  /**
   * @param end the end to set
   */
  public void setEnd(Integer end) {
    this.end = end;
  }
}
