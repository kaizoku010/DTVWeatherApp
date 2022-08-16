package com.hava.dvtweatherapp.ui.holders;

    public class ScreenItem {
        public String getTitle() {
            return Title;
        }
        public void setTitle(String title) {
            Title = title;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }

        public int getScreenImage() {
            return ScreenImage;
        }

        public void setScreenImage(int screenImage) {
            ScreenImage = screenImage;
        }

        String Title;

        public String getHeading() {
            return heading;
        }

        public void setHeading(String heading) {
            this.heading = heading;
        }

        String heading;
        String Description;
        int ScreenImage;
        int TitleVisibility;
        public int getTitleVisibility() {
            return TitleVisibility;
        }

        public void setTitleVisibility(int titleVisibility) {
            TitleVisibility = titleVisibility;
        }

        public ScreenItem(String title, String description, int screenImage, String heading_, int titleVisibility_) {
            Title = title;
            TitleVisibility = titleVisibility_;
            heading = heading_;
            Description = description;
            ScreenImage = screenImage;
        }


    }


