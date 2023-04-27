package dk.sdu.group.one.data;

import dk.sdu.group.one.map.MapService;

public abstract class Entity {
    String texturePath;
    int x, y;
    float radians;
    int maxHealth;
    int currentHealth;


    EntityType type;

    public Entity(EntityType entityType, String spritePath, int x, int y, int maxHealth) {
        this.type = entityType;
        this.texturePath = spritePath;
        this.x = x;
        this.y = y;
        this.maxHealth = maxHealth;
        this.currentHealth = this.maxHealth;
    }
    
    public Entity(EntityType entityType, String spritePath, int x, int y) {
        this.type = entityType;
        this.texturePath = spritePath;
        this.x = x;
        this.y = y;
    }

    public EntityType getType() {
        return this.type;
    }

    public int getX() {
        return this.x;
    }

    public void setRadians(float rads) {
        this.radians = rads;
    }

    public float getRadians() {
        return this.radians;
    }

    public int getY() {
        return this.y;
    }

    public String getTexturePath() {
        return this.texturePath;
    }

    public abstract void process(EntityManager entityManager);

    public abstract void start(MapService mapService, EntityManager entityList);

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        if(getCurrentHealth()>maxHealth){
            setCurrentHealth(maxHealth);
        }
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void takeDamage(int amount){
        this.currentHealth -= amount;
        if(getCurrentHealth() < 1){
            //dies
        }
    }

    public void heal(int amount){
        this.currentHealth += amount;
        if(getCurrentHealth() > getMaxHealth()){
            setCurrentHealth(getMaxHealth());
        }
    }

}