package org.framework42.web.components.standardhtml.head;

public enum HeadLinkRelationship {
   ALTERNATE("alternate"), APPENDIX("appendix"), BOOKMARK("bookmark"), CHAPTER("chapter"), CONTENTS("contents"),
   COPYRIGHT("copyright"), GLOSSARY("glossary"), HELP("help"), HOME("home"), INDEX("index"), NEXT("next"),
   PREV("prev"), SECTION("section"), SHORTCUT_ICON("shortcut icon"), START("start"), STYLESHEET("stylesheet"),
   SUBSECTION("subsection");

   private final String id;

   private HeadLinkRelationship(String id) {
      this.id = id;
   }

   public String getId() {
      return id;
   }

   @Override
   public String toString() {
      return id;
   }

}
