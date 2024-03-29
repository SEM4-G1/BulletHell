package dk.sdu.group.one.data;

import dk.sdu.group.one.map.MapService;

public abstract class Entity {
    private String texturePath;
    float x, y;
    int width, height;
    float radians;
    int maxHealth;
    int currentHealth;

    // Initialize movement vector as 0,0
    private Vector2 velocity = new Vector2(0, 0);

    EntityType type;

    public Entity(EntityType entityType, String spritePath, float x, float y, int width, int height, int maxHealth) {
        this.type = entityType;
        this.texturePath = spritePath;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.maxHealth = maxHealth;
        this.currentHealth = this.maxHealth;
    }
    
    public Entity(EntityType entityType, String spritePath, float x, float y, int width, int height) {
        this.type = entityType;
        this.texturePath = spritePath;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public EntityType getType() {
        return this.type;
    }

    public float getX() {
        return this.x;
    }

    public void setRadians(float rads) {
        this.radians = rads;
    }

    public float getRadians() {
        return this.radians;
    }

    public float getY() {
        return this.y;
    }

    public String getTexturePath() {
        return this.texturePath;
    }

    public abstract void process(EntityManager entityManager, double dt);

    public abstract void start(MapService mapService, EntityManager entityList);

    public int getMaxHealth() {
        return maxHealth;
    }

    public Vector2 getVelocity() {
        return this.velocity;
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

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName() + ", x: "+ getX() + ", y: " +getY() + "type: " + getType();
    }

    public void setTexturePath(String texturePath) {
        this.texturePath = texturePath;
    }
}